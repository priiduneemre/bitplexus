package com.neemre.bitplexus.backend.service.impl;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.neemre.bitplexus.backend.crypto.BitcoinWrapperException;
import com.neemre.bitplexus.backend.crypto.LitecoinWrapperException;
import com.neemre.bitplexus.backend.crypto.LockedTransactionPool;
import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.crypto.adapter.NodeClientAdapter;
import com.neemre.bitplexus.backend.data.AddressRepository;
import com.neemre.bitplexus.backend.data.CurrencyRepository;
import com.neemre.bitplexus.backend.data.TransactionEndpointRepository;
import com.neemre.bitplexus.backend.data.TransactionRepository;
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
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.TransactionDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;
import com.neemre.bitplexus.common.dto.virtual.PaymentDetailsDto;
import com.neemre.bitplexus.common.util.CollectionUtils;
import com.neemre.bitplexus.common.util.DateUtils;
import com.neemre.bitplexus.common.util.StringUtils;

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
	@Resource(name = "lockedTransactionPool")
	private LockedTransactionPool lockedTransactions;
	@Autowired
	private NodeClientAdapter nodeClient;

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
	public BigDecimal findTransactionOptimalFee(BigDecimal requiredAmount, Integer walletId, 
			String chainCode) {
		return findTransactionMinimumFee(chainCode);
	}

	@Transactional
	@Override
	public TransactionDto receiveNewTransaction(String networkUid, String chainCode) 
			throws NodeWrapperException {
		if (!lockedTransactions.addTransaction(networkUid)) {
			return null;
		}
		Transaction transaction = new Transaction();
		if (nodeClient.isOperationalBtcChain(chainCode)) {
			com.neemre.btcdcli4j.core.domain.Transaction networkTransaction = 
					nodeClient.getBtcTransaction(networkUid, chainCode);
			com.neemre.btcdcli4j.core.domain.RawTransaction rawNetworkTransaction = 
					nodeClient.getBtcRawTransaction(networkUid, chainCode);
			transaction.setReceivedAt(DateUtils.toDate(networkTransaction.getTimeReceived()));
			if (networkTransaction.getBlockHash() != null) {
				com.neemre.btcdcli4j.core.domain.Block containingBlock = nodeClient.getBtcBlock(
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
		} else if (nodeClient.isOperationalLtcChain(chainCode)) {
			com.neemre.ltcdcli4j.core.domain.Transaction networkTransaction = 
					nodeClient.getLtcTransaction(networkUid, chainCode);
			com.neemre.ltcdcli4j.core.domain.RawTransaction rawNetworkTransaction = 
					nodeClient.getLtcRawTransaction(networkUid, chainCode);
			transaction.setReceivedAt(DateUtils.toDate(networkTransaction.getTimeReceived()));
			if (networkTransaction.getBlockHash() != null) {
				com.neemre.ltcdcli4j.core.domain.Block containingBlock = nodeClient.getLtcBlock(
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
		lockedTransactions.removeTransaction(receivedTransaction.getNetworkUid());
		return dtoAssembler.assemble(receivedTransaction, Transaction.class, TransactionDto.class);
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private Date getBtcIncomingTxnCompletedAt(com.neemre.btcdcli4j.core.domain.Block containingBlock,
			String chainCode) throws BitcoinWrapperException {
		if (containingBlock.getConfirmations() >= Defaults.BTC_COMPLETED_CONF_COUNT) {
			return DateUtils.toDate(nodeClient.getBtcBlock(containingBlock.getHeight() + 
					(Defaults.BTC_COMPLETED_CONF_COUNT - 1), chainCode).getTime());
		}
		return null;
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private Date getLtcIncomingTxnCompletedAt(com.neemre.ltcdcli4j.core.domain.Block containingBlock,
			String chainCode) throws LitecoinWrapperException {
		if (containingBlock.getConfirmations() >= Defaults.LTC_COMPLETED_CONF_COUNT) {
			return DateUtils.toDate(nodeClient.getLtcBlock(containingBlock.getHeight() + 
					(Defaults.LTC_COMPLETED_CONF_COUNT - 1), chainCode).getTime());
		}
		return null;
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private BigDecimal getBtcIncomingTxnFee(List<com.neemre.btcdcli4j.core.domain.RawInput> 
			networkInputs, List<com.neemre.btcdcli4j.core.domain.RawOutput> networkOutputs,
			String chainCode) throws BitcoinWrapperException {
		BigDecimal inputSum = BigDecimal.ZERO;
		for (com.neemre.btcdcli4j.core.domain.RawInput networkInput : networkInputs) {
			inputSum = inputSum.add(nodeClient.getBtcRawTransaction(networkInput.getTxId(), 
					chainCode).getVOut().get(networkInput.getVOut()).getValue());
		}
		BigDecimal outputSum = BigDecimal.ZERO;
		for (com.neemre.btcdcli4j.core.domain.RawOutput networkOutput : networkOutputs) {
			outputSum = outputSum.add(networkOutput.getValue());
		}
		return inputSum.subtract(outputSum);
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private BigDecimal getLtcIncomingTxnFee(List<com.neemre.ltcdcli4j.core.domain.RawInput>
			networkInputs, List<com.neemre.ltcdcli4j.core.domain.RawOutput> networkOutputs,
			String chainCode) throws LitecoinWrapperException {
		BigDecimal inputSum = BigDecimal.ZERO;
		for (com.neemre.ltcdcli4j.core.domain.RawInput networkInput : networkInputs) {
			inputSum = inputSum.add(nodeClient.getLtcRawTransaction(networkInput.getTxId(), 
					chainCode).getVOut().get(networkInput.getVOut()).getValue());
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
		String encodedForm = Iterables.getOnlyElement(nodeClient.getBtcRawTransaction(
				networkInput.getTxId(), chainCode).getVOut().get(networkInput.getVOut())
				.getScriptPubKey().getAddresses());
		if (addressRepository.findByEncodedForm(encodedForm) == null) {
			addressService.createNewExternalAddress(new AddressDto(null, null, null, null, null, 
					encodedForm, null, null, null), chainCode);
		}
		transactionInput.setAddress(addressRepository.findByEncodedForm(encodedForm));
		transactionInput.setTransactionEndpointType(transactionEndpointRepository
				.findTransactionEndpointTypeByCode(TransactionEndpointTypes.INPUT.name()));
		transactionInput.setAmount(nodeClient.getBtcRawTransaction(networkInput.getTxId(), 
				chainCode).getVOut().get(networkInput.getVOut()).getValue());
		return transactionInput;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private TransactionEndpoint buildLtcIncomingTxnInput(com.neemre.ltcdcli4j.core.domain.RawInput 
			networkInput, String chainCode) throws LitecoinWrapperException {
		TransactionEndpoint transactionInput = new TransactionEndpoint();
		String encodedForm = Iterables.getOnlyElement(nodeClient.getLtcRawTransaction(
				networkInput.getTxId(), chainCode).getVOut().get(networkInput.getVOut())
				.getScriptPubKey().getAddresses());
		if (addressRepository.findByEncodedForm(encodedForm) == null) {
			addressService.createNewExternalAddress(new AddressDto(null, null, null, null, null,
					encodedForm, null, null, null), chainCode);
		}
		transactionInput.setAddress(addressRepository.findByEncodedForm(encodedForm));
		transactionInput.setTransactionEndpointType(transactionEndpointRepository
				.findTransactionEndpointTypeByCode(TransactionEndpointTypes.INPUT.name()));
		transactionInput.setAmount(nodeClient.getLtcRawTransaction(networkInput.getTxId(), 
				chainCode).getVOut().get(networkInput.getVOut()).getValue());
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

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
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

	@Transactional
	@Override
	public TransactionDto sendNewTransaction(PaymentDetailsDto paymentDetailsDto, Integer walletId, 
			String chainCode) throws NodeWrapperException {
		Transaction transaction = new Transaction();
		BigDecimal fee = findTransactionOptimalFee(paymentDetailsDto.getAmount(), walletId, chainCode);
		if (nodeClient.isOperationalBtcChain(chainCode)) {
			List<com.neemre.btcdcli4j.core.domain.Output> unspentOutputs = nodeClient.getBtcUnspentOutputs(
					addressRepository.findByWalletIdAndChainCode(walletId, chainCode), chainCode);
			checkBtcOutgoingTxnSufficientFunds(unspentOutputs, paymentDetailsDto.getAmount(), fee);
			List<com.neemre.btcdcli4j.core.domain.Output> selectedOutputs = selectBtcOutgoingTxnOutputs(
					unspentOutputs, paymentDetailsDto.getAmount().add(fee), chainCode);
			Map<String, BigDecimal> recipients = buildBtcOutgoingTxnRecipientList(selectedOutputs, 
					paymentDetailsDto, fee, walletId, chainCode);
			String networkUid = nodeClient.sendBtcRawTransaction(nodeClient.signBtcRawTransaction(
					nodeClient.createBtcRawTransaction(selectedOutputs, recipients, chainCode), 
					chainCode), chainCode);
			lockedTransactions.addTransaction(networkUid);
			com.neemre.btcdcli4j.core.domain.Transaction networkTransaction = 
					nodeClient.getBtcTransaction(networkUid, chainCode);
			com.neemre.btcdcli4j.core.domain.RawTransaction rawNetworkTransaction = 
					nodeClient.getBtcRawTransaction(networkUid, chainCode);
			transaction.setNetworkUid(networkUid);
			transaction.setReceivedAt(DateUtils.toDate(networkTransaction.getTimeReceived()));
			transaction.setBinarySize(StringUtils.toByteArray(networkTransaction.getHex()).length);
			for (com.neemre.btcdcli4j.core.domain.RawInput networkInput : rawNetworkTransaction.getVIn()) {
				transaction.addTransactionEndpoint(buildBtcOutgoingTxnInput(networkInput, chainCode));
			}
			for (com.neemre.btcdcli4j.core.domain.RawOutput networkOutput : rawNetworkTransaction.getVOut()) {
				transaction.addTransactionEndpoint(buildBtcOutgoingTxnOutput(networkOutput, 
						paymentDetailsDto.getRecipientAddress(), chainCode));
			}
		} else if (nodeClient.isOperationalLtcChain(chainCode)) {
			List<com.neemre.ltcdcli4j.core.domain.Output> unspentOutputs = nodeClient.getLtcUnspentOutputs(
					addressRepository.findByWalletIdAndChainCode(walletId, chainCode), chainCode);
			checkLtcOutgoingTxnSufficientFunds(unspentOutputs, paymentDetailsDto.getAmount(), fee);
			List<com.neemre.ltcdcli4j.core.domain.Output> selectedOutputs = selectLtcOutgoingTxnOutputs(
					unspentOutputs, paymentDetailsDto.getAmount().add(fee), chainCode);
			Map<String, BigDecimal> recipients = buildLtcOutgoingTxnRecipientList(selectedOutputs,
					paymentDetailsDto, fee, walletId, chainCode);
			String networkUid = nodeClient.sendLtcRawTransaction(nodeClient.signLtcRawTransaction(
					nodeClient.createLtcRawTransaction(selectedOutputs, recipients, chainCode), 
					chainCode), chainCode);
			lockedTransactions.addTransaction(networkUid);
			com.neemre.ltcdcli4j.core.domain.Transaction networkTransaction = 
					nodeClient.getLtcTransaction(networkUid, chainCode);
			com.neemre.ltcdcli4j.core.domain.RawTransaction rawNetworkTransaction = 
					nodeClient.getLtcRawTransaction(networkUid, chainCode);
			transaction.setNetworkUid(networkUid);
			transaction.setReceivedAt(DateUtils.toDate(networkTransaction.getTimeReceived()));
			transaction.setBinarySize(StringUtils.toByteArray(networkTransaction.getHex()).length);
			for (com.neemre.ltcdcli4j.core.domain.RawInput networkInput : rawNetworkTransaction.getVIn()) {
				transaction.addTransactionEndpoint(buildLtcOutgoingTxnInput(networkInput, chainCode));
			}
			for (com.neemre.ltcdcli4j.core.domain.RawOutput networkOutput : rawNetworkTransaction.getVOut()) {
				transaction.addTransactionEndpoint(buildLtcOutgoingTxnOutput(networkOutput, 
						paymentDetailsDto.getRecipientAddress(), chainCode));
			}
		}
		transaction.setTransactionStatusType(transactionRepository.findTransactionStatusTypeByCode(
				TransactionStatusTypes.UNCONFIRMED.name()));
		transaction.setFee(fee);
		transaction.setUnitPrice(chainService.findChainUnitPrice(chainCode));
		transaction.setNote(paymentDetailsDto.getNote());
		Transaction sentTransaction = transactionRepository.saveAndFlush(transaction);
		lockedTransactions.removeTransaction(sentTransaction.getNetworkUid());
		return dtoAssembler.assemble(sentTransaction, Transaction.class, TransactionDto.class);
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private void checkBtcOutgoingTxnSufficientFunds(List<com.neemre.btcdcli4j.core.domain.Output>
			unspentOutputs, BigDecimal requiredAmount, BigDecimal fee) {
		if (getBtcOutgoingTxnOutputSum(unspentOutputs).compareTo(requiredAmount.add(fee)) < 0) {
			throw new IllegalStateException(Errors.TODO.getDescription());
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private void checkLtcOutgoingTxnSufficientFunds(List<com.neemre.ltcdcli4j.core.domain.Output>
			unspentOutputs, BigDecimal requiredAmount, BigDecimal fee) {
		if (getLtcOutgoingTxnOutputSum(unspentOutputs).compareTo(requiredAmount.add(fee)) < 0) {
			throw new IllegalStateException(Errors.TODO.getDescription());
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private BigDecimal getBtcOutgoingTxnOutputSum(
			List<com.neemre.btcdcli4j.core.domain.Output> networkOutputs) {
		BigDecimal outputSum = BigDecimal.ZERO;
		if (networkOutputs != null) {
			for (com.neemre.btcdcli4j.core.domain.Output networkOutput : networkOutputs) {
				if (networkOutput != null) {
					outputSum = outputSum.add(networkOutput.getAmount());
				}
			}
		}
		return outputSum;
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private BigDecimal getLtcOutgoingTxnOutputSum(
			List<com.neemre.ltcdcli4j.core.domain.Output> networkOutputs) {
		BigDecimal outputSum = BigDecimal.ZERO;
		if (networkOutputs != null) {
			for (com.neemre.ltcdcli4j.core.domain.Output networkOutput : networkOutputs) {
				if (networkOutput != null) {
					outputSum = outputSum.add(networkOutput.getAmount());
				}
			}
		}
		return outputSum;
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private List<com.neemre.btcdcli4j.core.domain.Output> selectBtcOutgoingTxnOutputs(
			List<com.neemre.btcdcli4j.core.domain.Output> unspentOutputs, BigDecimal requiredAmount, 
			String chainCode) {
		for (int i = 0; i < unspentOutputs.size(); i++) {
			if (unspentOutputs.get(i).getAmount().compareTo(requiredAmount) == 0) {
				return Arrays.asList(unspentOutputs.remove(i));
			}
		}
		List<com.neemre.btcdcli4j.core.domain.Output> selectedOutputs = 
				new ArrayList<com.neemre.btcdcli4j.core.domain.Output>();
		BigDecimal allocatedAmount = BigDecimal.ZERO;
		while (allocatedAmount.compareTo(requiredAmount) < 0) {
			com.neemre.btcdcli4j.core.domain.Output selectedOutput = unspentOutputs.remove(
					new SecureRandom().nextInt(unspentOutputs.size()));
			selectedOutputs.add(selectedOutput);
			allocatedAmount = allocatedAmount.add(selectedOutput.getAmount());
		}
		return selectedOutputs;
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private List<com.neemre.ltcdcli4j.core.domain.Output> selectLtcOutgoingTxnOutputs(
			List<com.neemre.ltcdcli4j.core.domain.Output> unspentOutputs, BigDecimal requiredAmount,
			String chainCode) {
		for (int i = 0; i < unspentOutputs.size(); i++) {
			if (unspentOutputs.get(i).getAmount().compareTo(requiredAmount) == 0) {
				return Arrays.asList(unspentOutputs.remove(i));
			}
		}
		List<com.neemre.ltcdcli4j.core.domain.Output> selectedOutputs = 
				new ArrayList<com.neemre.ltcdcli4j.core.domain.Output>();
		BigDecimal allocatedAmount = BigDecimal.ZERO;
		while (allocatedAmount.compareTo(requiredAmount) < 0) {
			com.neemre.ltcdcli4j.core.domain.Output selectedOutput = unspentOutputs.remove(
					new SecureRandom().nextInt(unspentOutputs.size()));
			selectedOutputs.add(selectedOutput);
			allocatedAmount = allocatedAmount.add(selectedOutput.getAmount());
		}
		return selectedOutputs;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private Map<String, BigDecimal> buildBtcOutgoingTxnRecipientList(List<com.neemre.btcdcli4j.core
			.domain.Output> selectedOutputs, PaymentDetailsDto paymentDetailsDto, BigDecimal fee, 
			Integer walletId, String chainCode) throws NodeWrapperException {
		Map<String, BigDecimal> recipients = new HashMap<String, BigDecimal>();
		BigDecimal excessAmount = getBtcOutgoingTxnOutputSum(selectedOutputs).subtract(
				paymentDetailsDto.getAmount().add(fee));
		recipients.put(paymentDetailsDto.getRecipientAddress(), paymentDetailsDto.getAmount());
		if (excessAmount.compareTo(BigDecimal.ZERO) > 0) {
			AddressDto changeAddressDto = addressService.createNewWalletAddress(new AddressDto(null, 
					walletId, null, null, null, null, null, null, null), true, chainCode);
			recipients.put(changeAddressDto.getEncodedForm(), excessAmount);
		}
		recipients = CollectionUtils.shuffle(recipients);
		return recipients;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private Map<String, BigDecimal> buildLtcOutgoingTxnRecipientList(List<com.neemre.ltcdcli4j.core
			.domain.Output> selectedOutputs, PaymentDetailsDto paymentDetailsDto, BigDecimal fee,
			Integer walletId, String chainCode) throws NodeWrapperException {
		Map<String, BigDecimal> recipients = new HashMap<String, BigDecimal>();
		BigDecimal excessAmount = getLtcOutgoingTxnOutputSum(selectedOutputs).subtract(
				paymentDetailsDto.getAmount().add(fee));
		recipients.put(paymentDetailsDto.getRecipientAddress(), paymentDetailsDto.getAmount());
		if (excessAmount.compareTo(BigDecimal.ZERO) > 0) {
			AddressDto changeAddressDto = addressService.createNewWalletAddress(new AddressDto(null,
					walletId, null, null, null, null, null, null, null), true, chainCode);
			recipients.put(changeAddressDto.getEncodedForm(), excessAmount);
		}
		recipients = CollectionUtils.shuffle(recipients);
		return recipients;
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private TransactionEndpoint buildBtcOutgoingTxnInput(com.neemre.btcdcli4j.core.domain.RawInput
			networkInput, String chainCode) throws BitcoinWrapperException {
		TransactionEndpoint transactionInput = new TransactionEndpoint();
		String encodedForm = Iterables.getOnlyElement(nodeClient.getBtcRawTransaction(
				networkInput.getTxId(), chainCode).getVOut().get(networkInput.getVOut())
				.getScriptPubKey().getAddresses());
		transactionInput.setAddress(addressRepository.findByEncodedForm(encodedForm));
		transactionInput.setTransactionEndpointType(transactionEndpointRepository
				.findTransactionEndpointTypeByCode(TransactionEndpointTypes.INPUT.name()));
		transactionInput.setAmount(nodeClient.getBtcRawTransaction(networkInput.getTxId(),
				chainCode).getVOut().get(networkInput.getVOut()).getValue());
		return transactionInput;
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private TransactionEndpoint buildLtcOutgoingTxnInput(com.neemre.ltcdcli4j.core.domain.RawInput
			networkInput, String chainCode) throws LitecoinWrapperException {
		TransactionEndpoint transactionInput = new TransactionEndpoint();
		String encodedForm = Iterables.getOnlyElement(nodeClient.getLtcRawTransaction(
				networkInput.getTxId(), chainCode).getVOut().get(networkInput.getVOut())
				.getScriptPubKey().getAddresses());
		transactionInput.setAddress(addressRepository.findByEncodedForm(encodedForm));
		transactionInput.setTransactionEndpointType(transactionEndpointRepository
				.findTransactionEndpointTypeByCode(TransactionEndpointTypes.INPUT.name()));
		transactionInput.setAmount(nodeClient.getLtcRawTransaction(networkInput.getTxId(),
				chainCode).getVOut().get(networkInput.getVOut()).getValue());
		return transactionInput;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private TransactionEndpoint buildBtcOutgoingTxnOutput(com.neemre.btcdcli4j.core.domain.RawOutput
			networkOutput, String recipientAddress, String chainCode) {
		TransactionEndpoint transactionOutput = new TransactionEndpoint();
		String encodedForm = Iterables.getOnlyElement(networkOutput.getScriptPubKey().getAddresses());
		if (addressRepository.findByEncodedForm(encodedForm) == null) {
			addressService.createNewExternalAddress(new AddressDto(null, null, null, null, null, 
					encodedForm, null, null, null), chainCode);
		}
		transactionOutput.setAddress(addressRepository.findByEncodedForm(encodedForm));
		if (transactionOutput.getAddress().getEncodedForm().equals(recipientAddress)) {
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
	private TransactionEndpoint buildLtcOutgoingTxnOutput(com.neemre.ltcdcli4j.core.domain.RawOutput
			networkOutput, String recipientAddress, String chainCode) {
		TransactionEndpoint transactionOutput = new TransactionEndpoint();
		String encodedForm = Iterables.getOnlyElement(networkOutput.getScriptPubKey().getAddresses());
		if (addressRepository.findByEncodedForm(encodedForm) == null) {
			addressService.createNewExternalAddress(new AddressDto(null, null, null, null, null,
					encodedForm, null, null, null), chainCode);
		}
		transactionOutput.setAddress(addressRepository.findByEncodedForm(encodedForm));
		if (transactionOutput.getAddress().getEncodedForm().equals(recipientAddress)) {
			transactionOutput.setTransactionEndpointType(transactionEndpointRepository
					.findTransactionEndpointTypeByCode(TransactionEndpointTypes.OUTPUT_MAIN.name()));
		} else {
			transactionOutput.setTransactionEndpointType(transactionEndpointRepository
					.findTransactionEndpointTypeByCode(TransactionEndpointTypes.OUTPUT_CHANGE.name()));
		}
		transactionOutput.setAmount(networkOutput.getValue());
		return transactionOutput;
	}
}