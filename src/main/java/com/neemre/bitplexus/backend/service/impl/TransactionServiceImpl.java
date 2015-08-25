package com.neemre.bitplexus.backend.service.impl;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
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
import com.neemre.bitplexus.backend.model.enums.Currencies;
import com.neemre.bitplexus.backend.model.enums.TransactionStatusTypes;
import com.neemre.bitplexus.backend.model.Transaction;
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.common.Constants;
import com.neemre.bitplexus.common.Defaults;
import com.neemre.bitplexus.common.Errors;
import com.neemre.bitplexus.common.PropertyKeys;
import com.neemre.bitplexus.common.dto.TransactionDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;
import com.neemre.bitplexus.common.dto.virtual.PaymentDetailsDto;
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
	private TransactionRepository transactionRepository;
	@Autowired
	private TransactionEndpointRepository transactionEndpointRepository;

	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	@Autowired
	private NodeWrapperResolver clientResolver;


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
			String chainCode) throws BitcoinWrapperException {
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
			String chainCode) throws LitecoinWrapperException {
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
	private String createRawBtcTransaction(PaymentDetailsDto paymentDetailsDto,
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
	private String createRawLtcTransaction(PaymentDetailsDto paymentDetailsDto,
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
	private String signRawBtcTransaction(String hexTransaction, String chainCode) 
			throws BitcoinWrapperException {
		try {
			BtcdClient btcdClient = clientResolver.getBtcdClient(chainCode); 
			setBtcWalletPassphrase(btcdClient.getNodeConfig().getProperty(
					PropertyKeys.BITCOIND_WALLET_PASSPHRASE.getValue()), chainCode);
			return btcdClient.signRawTransaction(hexTransaction).getHex();
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String signRawLtcTransaction(String hexTransaction, String chainCode)
			throws LitecoinWrapperException {
		try {
			LtcdClient ltcdClient = clientResolver.getLtcdClient(chainCode);
			setLtcWalletPassphrase(ltcdClient.getNodeConfig().getProperty(
					PropertyKeys.LITECOIND_WALLET_PASSPHRASE.getValue()), chainCode);
			return ltcdClient.signRawTransaction(hexTransaction).getHex();
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String sendRawBtcTransaction(String signedHexTransaction, String chainCode)
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
	private String sendRawLtcTransaction(String signedHexTransaction, String chainCode) 
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
	private com.neemre.btcdcli4j.core.domain.RawTransaction getRawBtcTransaction(String networkUid,
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
	private com.neemre.ltcdcli4j.core.domain.RawTransaction getRawLtcTransaction(String networkUid,
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
}