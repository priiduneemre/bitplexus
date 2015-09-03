package com.neemre.bitplexus.backend.service.impl;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.neemre.bitplexus.backend.crypto.BitcoinWrapperException;
import com.neemre.bitplexus.backend.crypto.LitecoinWrapperException;
import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.crypto.NodeWrapperResolver;
import com.neemre.bitplexus.backend.data.AddressRepository;
import com.neemre.bitplexus.backend.data.CurrencyRepository;
import com.neemre.bitplexus.backend.data.TransactionEndpointRepository;
import com.neemre.bitplexus.backend.data.TransactionRepository;
import com.neemre.bitplexus.backend.model.Address;
import com.neemre.bitplexus.backend.model.Address.EncodedFormExtractor;
import com.neemre.bitplexus.backend.model.Currency;
import com.neemre.bitplexus.backend.model.TransactionEndpoint;
import com.neemre.bitplexus.backend.model.enums.Currencies;
import com.neemre.bitplexus.backend.model.enums.TransactionEndpointTypes;
import com.neemre.bitplexus.backend.model.enums.TransactionStatusTypes;
import com.neemre.bitplexus.backend.model.reference.TransactionStatusType;
import com.neemre.bitplexus.backend.model.Transaction;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.backend.service.ChainService;
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.common.Constants;
import com.neemre.bitplexus.common.Defaults;
import com.neemre.bitplexus.common.Errors;
import com.neemre.bitplexus.common.PropertyKeys;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.TransactionDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;
import com.neemre.bitplexus.common.dto.virtual.PaymentDetailsDto;
import com.neemre.bitplexus.common.util.DateUtils;
import com.neemre.bitplexus.common.util.StringUtils;
import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.ltcdcli4j.core.LitecoindException;
import com.neemre.ltcdcli4j.core.client.LtcdClient;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired
	private TransactionEndpointRepository transactionEndpointRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	@Autowired
	private NodeWrapperResolver clientResolver;

	@Autowired
	private AddressService addressService;
	@Autowired
	private ChainService chainService;


	@Transactional
	@Override
	public List<String> completeTransactions(Integer blockHeight, Date blockTime, String chainCode) {
		String completedNetworkUidsCsv = Constants.STRING_EMPTY;
		Currency currency = currencyRepository.findByChainCode(chainCode);
		if (currency.getName().equals(Currencies.BITCOIN.getName())) {
			completedNetworkUidsCsv = transactionRepository.updateTransactionStatusTypesToCompleted(
					(short)Defaults.BTC_COMPLETED_CONF_COUNT, blockHeight, blockTime, chainCode); 
		} else if (currency.getName().equals(Currencies.LITECOIN.getName())) {
			completedNetworkUidsCsv = transactionRepository.updateTransactionStatusTypesToCompleted(
					(short)Defaults.LTC_COMPLETED_CONF_COUNT, blockHeight, blockTime, chainCode);
		} else {
			throw new IllegalArgumentException(Errors.TODO.getDescription());
		}
		return Lists.newArrayList(Splitter.on(Constants.STRING_COMMA).omitEmptyStrings().trimResults()
				.split(MoreObjects.firstNonNull(completedNetworkUidsCsv, Constants.STRING_EMPTY)
						.replaceAll(Constants.STRING_NULL, Constants.STRING_EMPTY)));
	}

	@Transactional
	@Override
	public List<String> confirmTransactions(List<String> networkUids, Integer blockHeight, 
			Date blockTime, String chainCode) {
		String networkUidsCsv = Joiner.on(Constants.STRING_COMMA).useForNull(Constants.STRING_NULL)
				.join(networkUids);
		String confirmedNetworkUidsCsv = transactionRepository.updateTransactionStatusTypesToConfirmed(
				networkUidsCsv, blockHeight, blockTime, chainCode);
		return Lists.newArrayList(Splitter.on(Constants.STRING_COMMA).omitEmptyStrings().trimResults()
				.split(MoreObjects.firstNonNull(confirmedNetworkUidsCsv, Constants.STRING_EMPTY)
						.replaceAll(Constants.STRING_NULL, Constants.STRING_EMPTY)));
	}

	@Transactional
	@Override
	public List<String> dropTransactions(String chainCode) {
		String droppedNetworkUidsCsv = Constants.STRING_EMPTY;
		Currency currency = currencyRepository.findByChainCode(chainCode);
		if (currency.getName().equals(Currencies.BITCOIN.getName())) {
			droppedNetworkUidsCsv = transactionRepository.updateTransactionStatusTypesToDropped(
					Defaults.BTC_TXN_TIMEOUT, chainCode);
		} else if (currency.getName().equals(Currencies.LITECOIN.getName())) {
			droppedNetworkUidsCsv = transactionRepository.updateTransactionStatusTypesToDropped(
					Defaults.LTC_TXN_TIMEOUT, chainCode);
		} else {
			throw new IllegalArgumentException(Errors.TODO.getDescription());
		}
		return Lists.newArrayList(Splitter.on(Constants.STRING_COMMA).omitEmptyStrings().trimResults()
				.split(MoreObjects.firstNonNull(droppedNetworkUidsCsv, Constants.STRING_EMPTY)
						.replaceAll(Constants.STRING_NULL, Constants.STRING_EMPTY)));
	}

	@Transactional(readOnly = true)
	@Override
	public List<TransactionDto> findSubwalletTransactions(Integer walletId, String chainCode) {
		List<Transaction> transactions = transactionRepository.findByWalletIdAndChainCode(walletId,
				chainCode);
		return dtoAssembler.assemble(transactions, Transaction.class, TransactionDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public TransactionDto findTransactionById(Long transactionId) {
		Transaction transaction = transactionRepository.findOne(transactionId);
		return dtoAssembler.assemble(transaction, Transaction.class, TransactionDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public TransactionDto findTransactionByNetworkUid(String networkUid) {
		Transaction transaction = transactionRepository.findByNetworkUid(networkUid);
		return dtoAssembler.assemble(transaction, Transaction.class,  TransactionDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public BigDecimal findTransactionMinimumFee(String chainCode) {
		Currency currency = currencyRepository.findByChainCode(chainCode);
		if (currency.getName().equals(Currencies.BITCOIN.getName())) {
			return transactionRepository.estimateFeeByHexTxnAndFeeCoefficient(currency.getName(),
					Constants.STRING_EMPTY, Defaults.BTC_TXN_FEE_COEFFICIENT);
		} else if (currency.getName().equals(Currencies.LITECOIN.getName())) {
			return transactionRepository.estimateFeeByHexTxnAndFeeCoefficient(currency.getName(),
					Constants.STRING_EMPTY, Defaults.LTC_TXN_FEE_COEFFICIENT);
		}
		throw new IllegalArgumentException(Errors.TODO.getDescription());
	}

	@Transactional(readOnly = true)
	@Override
	public BigDecimal findTransactionOptimalFee(String hexTransaction, String chainCode) {
		Currency currency = currencyRepository.findByChainCode(chainCode);
		if (currency.getName().equals(Currencies.BITCOIN.getName())) {
			return transactionRepository.estimateFeeByHexTxnAndFeeCoefficient(currency.getName(), 
					hexTransaction, Defaults.BTC_TXN_FEE_COEFFICIENT);
		} else if (currency.getName().equals(Currencies.LITECOIN.getName())) {
			return transactionRepository.estimateFeeByHexTxnAndFeeCoefficient(currency.getName(), 
					hexTransaction, Defaults.LTC_TXN_FEE_COEFFICIENT);
		}
		throw new IllegalArgumentException(Errors.TODO.getDescription());
	}

	@Transactional
	@Override
	public TransactionDto receiveNewTransaction(String networkUid, String chainCode) 
			throws NodeWrapperException {
		Transaction transaction = new Transaction();
		if (clientResolver.getBtcdClient(chainCode) != null) {
			com.neemre.btcdcli4j.core.domain.Transaction networkTransaction = 
					getBtcTransaction(networkUid, chainCode);
			com.neemre.btcdcli4j.core.domain.RawTransaction rawNetworkTransaction = 
					getBtcRawTransaction(networkUid, chainCode);
			transaction.setReceivedAt(DateUtils.toDate(networkTransaction.getTimeReceived()));
			if (networkTransaction.getBlockHash() != null) {
				com.neemre.btcdcli4j.core.domain.Block containingBlock = getBtcBlock(
						networkTransaction.getBlockHash(), chainCode);
				transaction.setConfirmedAt(DateUtils.toDate(containingBlock.getTime()));
				transaction.setCompletedAt(getBtcIncomingTxnCompletedAt(containingBlock, chainCode));
				transaction.setBlockHeight(containingBlock.getHeight());
			}
			transaction.setBinarySize(StringUtils.toByteArray(networkTransaction.getHex()).length);
			transaction.setFee(getBtcIncomingTxnFee(rawNetworkTransaction.getVIn(), 
					rawNetworkTransaction.getVOut(), chainCode));
			for (com.neemre.btcdcli4j.core.domain.RawInput networkInput : rawNetworkTransaction.getVIn()) {
				transaction.addTransactionEndpoint(buildBtcIncomingTxnInput(networkInput, chainCode));
			}
			for (com.neemre.btcdcli4j.core.domain.RawOutput networkOutput : rawNetworkTransaction.getVOut()) {
				transaction.addTransactionEndpoint(buildBtcIncomingTxnOutput(networkOutput, chainCode));
			}
		} else if (clientResolver.getLtcdClient(chainCode) != null) {
			com.neemre.ltcdcli4j.core.domain.Transaction networkTransaction = 
					getLtcTransaction(networkUid, chainCode);
			com.neemre.ltcdcli4j.core.domain.RawTransaction rawNetworkTransaction = 
					getLtcRawTransaction(networkUid, chainCode);
			transaction.setReceivedAt(DateUtils.toDate(networkTransaction.getTimeReceived()));
			if (networkTransaction.getBlockHash() != null) {
				com.neemre.ltcdcli4j.core.domain.Block containingBlock = getLtcBlock(
						networkTransaction.getBlockHash(), chainCode);
				transaction.setConfirmedAt(DateUtils.toDate(containingBlock.getTime()));
				transaction.setCompletedAt(getLtcIncomingTxnCompletedAt(containingBlock, chainCode));
				transaction.setBlockHeight(containingBlock.getHeight());
			}
			transaction.setBinarySize(StringUtils.toByteArray(networkTransaction.getHex()).length);
			transaction.setFee(getLtcIncomingTxnFee(rawNetworkTransaction.getVIn(), 
					rawNetworkTransaction.getVOut(), chainCode));
			for (com.neemre.ltcdcli4j.core.domain.RawInput networkInput : rawNetworkTransaction.getVIn()) {
				transaction.addTransactionEndpoint(buildLtcIncomingTxnInput(networkInput, chainCode));
			}
			for (com.neemre.ltcdcli4j.core.domain.RawOutput networkOutput : rawNetworkTransaction.getVOut()) {
				transaction.addTransactionEndpoint(buildLtcIncomingTxnOutput(networkOutput, chainCode));
			}
		}
		transaction.setTransactionStatusType(resolveIncomingTxnStatusType(transaction));
		transaction.setNetworkUid(networkUid);
		transaction.setUnitPrice(chainService.findChainUnitPrice(chainCode));
		Transaction receivedTransaction = transactionRepository.saveAndFlush(transaction);
		return dtoAssembler.assemble(receivedTransaction, Transaction.class, TransactionDto.class);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private Date getBtcIncomingTxnCompletedAt(com.neemre.btcdcli4j.core.domain.Block containingBlock,
			String chainCode) throws BitcoinWrapperException {
		if (containingBlock.getConfirmations() >= Defaults.BTC_COMPLETED_CONF_COUNT) {
			return DateUtils.toDate(getBtcBlock(containingBlock.getHeight() + 
					(Defaults.BTC_COMPLETED_CONF_COUNT - 1), chainCode).getTime());
		}
		return null;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private Date getLtcIncomingTxnCompletedAt(com.neemre.ltcdcli4j.core.domain.Block containingBlock,
			String chainCode) throws LitecoinWrapperException {
		if (containingBlock.getConfirmations() >= Defaults.LTC_COMPLETED_CONF_COUNT) {
			return DateUtils.toDate(getLtcBlock(containingBlock.getHeight() + 
					(Defaults.LTC_COMPLETED_CONF_COUNT - 1), chainCode).getTime());
		}
		return null;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private BigDecimal getBtcIncomingTxnFee(List<com.neemre.btcdcli4j.core.domain.RawInput> 
			networkInputs, List<com.neemre.btcdcli4j.core.domain.RawOutput> networkOutputs,
			String chainCode) throws BitcoinWrapperException {
		BigDecimal inputSum = BigDecimal.ZERO;
		for (com.neemre.btcdcli4j.core.domain.RawInput networkInput : networkInputs) {
			inputSum = inputSum.add(getBtcRawTransaction(networkInput.getTxId(), chainCode).getVOut()
					.get(networkInput.getVOut()).getValue());
		}
		BigDecimal outputSum = BigDecimal.ZERO;
		for (com.neemre.btcdcli4j.core.domain.RawOutput networkOutput : networkOutputs) {
			outputSum = outputSum.add(networkOutput.getValue());
		}
		return inputSum.subtract(outputSum);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private BigDecimal getLtcIncomingTxnFee(List<com.neemre.ltcdcli4j.core.domain.RawInput>
			networkInputs, List<com.neemre.ltcdcli4j.core.domain.RawOutput> networkOutputs,
			String chainCode) throws LitecoinWrapperException {
		BigDecimal inputSum = BigDecimal.ZERO;
		for (com.neemre.ltcdcli4j.core.domain.RawInput networkInput : networkInputs) {
			inputSum = inputSum.add(getLtcRawTransaction(networkInput.getTxId(), chainCode).getVOut()
					.get(networkInput.getVOut()).getValue());
		}
		BigDecimal outputSum = BigDecimal.ZERO;
		for (com.neemre.ltcdcli4j.core.domain.RawOutput networkOutput : networkOutputs) {
			outputSum = outputSum.add(networkOutput.getValue());
		}
		return inputSum.subtract(outputSum);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private TransactionEndpoint buildBtcIncomingTxnInput(com.neemre.btcdcli4j.core.domain.RawInput 
			networkInput, String chainCode) throws BitcoinWrapperException {
		TransactionEndpoint transactionInput = new TransactionEndpoint();
		String encodedForm = Iterables.getOnlyElement(getBtcRawTransaction(networkInput.getTxId(), 
				chainCode).getVOut().get(networkInput.getVOut()).getScriptPubKey().getAddresses());
		if (addressRepository.findByEncodedForm(encodedForm) == null) {
			addressService.createNewExternalAddress(new AddressDto(null, null, null, null, null, 
					encodedForm, null, null, null), chainCode);
		}
		transactionInput.setAddress(addressRepository.findByEncodedForm(encodedForm));
		transactionInput.setTransactionEndpointType(transactionEndpointRepository
				.findTransactionEndpointTypeByCode(TransactionEndpointTypes.INPUT.name()));
		transactionInput.setAmount(getBtcRawTransaction(networkInput.getTxId(), chainCode).getVOut()
				.get(networkInput.getVOut()).getValue());
		return transactionInput;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private TransactionEndpoint buildLtcIncomingTxnInput(com.neemre.ltcdcli4j.core.domain.RawInput 
			networkInput, String chainCode) throws LitecoinWrapperException {
		TransactionEndpoint transactionInput = new TransactionEndpoint();
		String encodedForm = Iterables.getOnlyElement(getLtcRawTransaction(networkInput.getTxId(), 
				chainCode).getVOut().get(networkInput.getVOut()).getScriptPubKey().getAddresses());
		if (addressRepository.findByEncodedForm(encodedForm) == null) {
			addressService.createNewExternalAddress(new AddressDto(null, null, null, null, null,
					encodedForm, null, null, null), chainCode);
		}
		transactionInput.setAddress(addressRepository.findByEncodedForm(encodedForm));
		transactionInput.setTransactionEndpointType(transactionEndpointRepository
				.findTransactionEndpointTypeByCode(TransactionEndpointTypes.INPUT.name()));
		transactionInput.setAmount(getLtcRawTransaction(networkInput.getTxId(), chainCode).getVOut()
				.get(networkInput.getVOut()).getValue());
		return transactionInput;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private TransactionEndpoint buildBtcIncomingTxnOutput(com.neemre.btcdcli4j.core.domain.RawOutput 
			networkOutput, String chainCode) {
		TransactionEndpoint transactionOutput = new TransactionEndpoint();
		String encodedForm = Iterables.getOnlyElement(networkOutput.getScriptPubKey().getAddresses());
		if (addressRepository.findByEncodedForm(encodedForm) == null) {
			addressService.createNewExternalAddress(new AddressDto(null, null, null, null, null, 
					encodedForm, null, null, null), chainCode);
		}
		transactionOutput.setAddress(addressRepository.findByEncodedForm(encodedForm));
		if (transactionOutput.getAddress().getWallet() != null) {
			transactionOutput.setTransactionEndpointType(transactionEndpointRepository
					.findTransactionEndpointTypeByCode(TransactionEndpointTypes.OUTPUT_MAIN.name()));
		} else {
			transactionOutput.setTransactionEndpointType(transactionEndpointRepository
					.findTransactionEndpointTypeByCode(TransactionEndpointTypes.OUTPUT_CHANGE.name()));
		}
		transactionOutput.setAmount(networkOutput.getValue());
		return transactionOutput;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private TransactionEndpoint buildLtcIncomingTxnOutput(com.neemre.ltcdcli4j.core.domain.RawOutput 
			networkOutput, String chainCode) {
		TransactionEndpoint transactionOutput = new TransactionEndpoint();
		String encodedForm = Iterables.getOnlyElement(networkOutput.getScriptPubKey().getAddresses());
		if (addressRepository.findByEncodedForm(encodedForm) == null) {
			addressService.createNewExternalAddress(new AddressDto(null, null, null, null, null, 
					encodedForm, null, null, null), chainCode);
		}
		transactionOutput.setAddress(addressRepository.findByEncodedForm(encodedForm));
		if (transactionOutput.getAddress().getWallet() != null) {
			transactionOutput.setTransactionEndpointType(transactionEndpointRepository
					.findTransactionEndpointTypeByCode(TransactionEndpointTypes.OUTPUT_MAIN.name()));
		} else {
			transactionOutput.setTransactionEndpointType(transactionEndpointRepository
					.findTransactionEndpointTypeByCode(TransactionEndpointTypes.OUTPUT_CHANGE.name()));
		}
		transactionOutput.setAmount(networkOutput.getValue());
		return transactionOutput;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private TransactionStatusType resolveIncomingTxnStatusType(Transaction transaction) {
		if (transaction.getCompletedAt() != null) {
			return transactionRepository.findTransactionStatusTypeByCode(
					TransactionStatusTypes.COMPLETED.name());
		} else if (transaction.getConfirmedAt() != null) {
			return transactionRepository.findTransactionStatusTypeByCode(
					TransactionStatusTypes.CONFIRMED.name());
		} else if (transaction.getReceivedAt() != null) {
			return transactionRepository.findTransactionStatusTypeByCode(
					TransactionStatusTypes.UNCONFIRMED.name());
		} else {
			throw new IllegalArgumentException(Errors.TODO.getDescription());
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private List<com.neemre.btcdcli4j.core.domain.Output> getBtcUnspentOutputs(
			List<Address> addresses, String chainCode) throws BitcoinWrapperException {
		try {
			List<String> encodedForms = Lists.transform(addresses, new EncodedFormExtractor());
			return clientResolver.getBtcdClient(chainCode).listUnspent(
					Defaults.BTC_COMPLETED_CONF_COUNT, Integer.MAX_VALUE, encodedForms);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private List<com.neemre.ltcdcli4j.core.domain.Output> getLtcUnspentOutputs(
			List<Address> addresses, String chainCode) throws LitecoinWrapperException {
		try {
			List<String> encodedForms = Lists.transform(addresses, new EncodedFormExtractor());
			return clientResolver.getLtcdClient(chainCode).listUnspent(
					Defaults.LTC_COMPLETED_CONF_COUNT, Integer.MAX_VALUE, encodedForms);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private List<com.neemre.btcdcli4j.core.domain.OutputOverview> selectBtcUnspentOutputs(
			List<com.neemre.btcdcli4j.core.domain.Output> unspentOutputs, BigDecimal requiredAmount, 
			String chainCode) {
		List<com.neemre.btcdcli4j.core.domain.OutputOverview> selectedOutputs = 
				new ArrayList<com.neemre.btcdcli4j.core.domain.OutputOverview>();
		for (int i = 0; i < unspentOutputs.size(); i++) {
			if (unspentOutputs.get(i).getAmount().compareTo(requiredAmount) == 0) {
				selectedOutputs.add(unspentOutputs.get(i));
				return selectedOutputs;
			}
		}
		BigDecimal allocatedAmount = BigDecimal.ZERO;
		for (int i = 0; i < unspentOutputs.size(); i++) {
			com.neemre.btcdcli4j.core.domain.Output selectedOutput = unspentOutputs.remove(
					new SecureRandom().nextInt(unspentOutputs.size()));
			selectedOutputs.add(selectedOutput);
			allocatedAmount = allocatedAmount.add(selectedOutput.getAmount());
			if (allocatedAmount.compareTo(requiredAmount) >= 0) {
				break;
			}
		}
		return selectedOutputs;
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private List<com.neemre.ltcdcli4j.core.domain.OutputOverview> selectLtcUnspentOutputs(
			List<com.neemre.ltcdcli4j.core.domain.Output> unspentOutputs, BigDecimal requiredAmount,
			String chainCode) {
		List<com.neemre.ltcdcli4j.core.domain.OutputOverview> selectedOutputs = 
				new ArrayList<com.neemre.ltcdcli4j.core.domain.OutputOverview>();
		for (int i = 0; i < unspentOutputs.size(); i++) {
			if (unspentOutputs.get(i).getAmount().compareTo(requiredAmount) == 0) {
				selectedOutputs.add(unspentOutputs.get(i));
				return selectedOutputs;
			}
		}
		BigDecimal allocatedAmount = BigDecimal.ZERO;
		for (int i = 0; i < unspentOutputs.size(); i++) {
			com.neemre.ltcdcli4j.core.domain.Output selectedOutput = unspentOutputs.remove(
					new SecureRandom().nextInt(unspentOutputs.size()));
			selectedOutputs.add(selectedOutput);
			allocatedAmount = allocatedAmount.add(selectedOutput.getAmount());
			if (allocatedAmount.compareTo(requiredAmount) >= 0) {
				break;
			}
		}
		return selectedOutputs;
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String createBtcRawTransaction(PaymentDetailsDto paymentDetailsDto,
			List<com.neemre.btcdcli4j.core.domain.OutputOverview> selectedOutputs, String chainCode) 
					throws BitcoinWrapperException {
		try {
			return clientResolver.getBtcdClient(chainCode).createRawTransaction(selectedOutputs, 
					ImmutableMap.of(paymentDetailsDto.getRecipientAddress(), paymentDetailsDto
							.getAmount()));
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String createLtcRawTransaction(PaymentDetailsDto paymentDetailsDto,
			List<com.neemre.ltcdcli4j.core.domain.OutputOverview> selectedOutputs, String chainCode) 
					throws LitecoinWrapperException {
		try {
			return clientResolver.getLtcdClient(chainCode).createRawTransaction(selectedOutputs, 
					ImmutableMap.of(paymentDetailsDto.getRecipientAddress(), paymentDetailsDto
							.getAmount()));
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private void setBtcWalletPassphrase(String passphrase, String chainCode) 
			throws BitcoinWrapperException {
		try {
			clientResolver.getBtcdClient(chainCode).walletPassphrase(passphrase, 
					Defaults.PASSPHRASE_TIMEOUT);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private void setLtcWalletPassphrase(String passphrase, String chainCode) 
			throws LitecoinWrapperException {
		try {
			clientResolver.getLtcdClient(chainCode).walletPassphrase(passphrase, 
					Defaults.PASSPHRASE_TIMEOUT);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String signBtcRawTransaction(String hexTransaction, String chainCode) 
			throws BitcoinWrapperException {
		BtcdClient btcdClient = clientResolver.getBtcdClient(chainCode); 
		int failedAttempts = 0;
		while (true) {
			try {
				setBtcWalletPassphrase(btcdClient.getNodeConfig().getProperty(
						PropertyKeys.BITCOIND_WALLET_PASSPHRASE.getValue()), chainCode);
				return btcdClient.signRawTransaction(hexTransaction).getHex();
			} catch (BitcoindException e) {
				failedAttempts++;
				if (failedAttempts >= Defaults.BTC_TXN_MAX_SIG_ATTEMPTS) {
					throw new BitcoinWrapperException(Errors.TODO, e);
				}
			} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
				throw new BitcoinWrapperException(Errors.TODO, e);
			}
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String signLtcRawTransaction(String hexTransaction, String chainCode)
			throws LitecoinWrapperException {
		LtcdClient ltcdClient = clientResolver.getLtcdClient(chainCode);
		int failedAttempts = 0;
		while (true) {
			try {
				setLtcWalletPassphrase(ltcdClient.getNodeConfig().getProperty(
						PropertyKeys.LITECOIND_WALLET_PASSPHRASE.getValue()), chainCode);
				return ltcdClient.signRawTransaction(hexTransaction).getHex();
			} catch (LitecoindException e) {
				failedAttempts++;
				if (failedAttempts >= Defaults.LTC_TXN_MAX_SIG_ATTEMPTS) {
					throw new LitecoinWrapperException(Errors.TODO, e);
				}
			} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
				throw new LitecoinWrapperException(Errors.TODO, e);
			}
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String sendBtcRawTransaction(String signedHexTransaction, String chainCode)
			throws BitcoinWrapperException {
		try {
			return clientResolver.getBtcdClient(chainCode).sendRawTransaction(signedHexTransaction,
					Defaults.ALLOW_HIGH_FEES);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String sendLtcRawTransaction(String signedHexTransaction, String chainCode) 
			throws LitecoinWrapperException {
		try {
			return clientResolver.getLtcdClient(chainCode).sendRawTransaction(signedHexTransaction,
					Defaults.ALLOW_HIGH_FEES);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private com.neemre.btcdcli4j.core.domain.Transaction getBtcTransaction(String networkUid,
			String chainCode) throws BitcoinWrapperException {
		try {
			return clientResolver.getBtcdClient(chainCode).getTransaction(networkUid);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private com.neemre.ltcdcli4j.core.domain.Transaction getLtcTransaction(String networkUid,
			String chainCode) throws LitecoinWrapperException {
		try {
			return clientResolver.getLtcdClient(chainCode).getTransaction(networkUid);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private com.neemre.btcdcli4j.core.domain.RawTransaction getBtcRawTransaction(String networkUid,
			String chainCode) throws BitcoinWrapperException {
		try {
			return (com.neemre.btcdcli4j.core.domain.RawTransaction)clientResolver.getBtcdClient(
					chainCode).getRawTransaction(networkUid, Defaults.RAW_TXN_VERBOSITY);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private com.neemre.ltcdcli4j.core.domain.RawTransaction getLtcRawTransaction(String networkUid,
			String chainCode) throws LitecoinWrapperException {
		try {
			return (com.neemre.ltcdcli4j.core.domain.RawTransaction)clientResolver.getLtcdClient(
					chainCode).getRawTransaction(networkUid, Defaults.RAW_TXN_VERBOSITY);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private com.neemre.btcdcli4j.core.domain.Block getBtcBlock(String headerHash, String chainCode) 
			throws BitcoinWrapperException {
		try {
			return (com.neemre.btcdcli4j.core.domain.Block)clientResolver.getBtcdClient(chainCode)
					.getBlock(headerHash, Defaults.BLOCK_VERBOSITY);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private com.neemre.ltcdcli4j.core.domain.Block getLtcBlock(String headerHash, String chainCode) 
			throws LitecoinWrapperException {
		try {
			return (com.neemre.ltcdcli4j.core.domain.Block)clientResolver.getLtcdClient(chainCode)
					.getBlock(headerHash, Defaults.BLOCK_VERBOSITY);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private com.neemre.btcdcli4j.core.domain.Block getBtcBlock(Integer blockHeight, String chainCode) 
			throws BitcoinWrapperException {
		try {
			BtcdClient btcdClient = clientResolver.getBtcdClient(chainCode);
			return (com.neemre.btcdcli4j.core.domain.Block)btcdClient.getBlock(
					btcdClient.getBlockHash(blockHeight), Defaults.BLOCK_VERBOSITY);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private com.neemre.ltcdcli4j.core.domain.Block getLtcBlock(Integer blockHeight, String chainCode) 
			throws LitecoinWrapperException {
		try {
			LtcdClient ltcdClient = clientResolver.getLtcdClient(chainCode);
			return (com.neemre.ltcdcli4j.core.domain.Block)ltcdClient.getBlock(
					ltcdClient.getBlockHash(blockHeight), Defaults.BLOCK_VERBOSITY);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}
}