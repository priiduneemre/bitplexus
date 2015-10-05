package com.neemre.bitplexus.backend.data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.neemre.bitplexus.backend.model.Transaction;
import com.neemre.bitplexus.backend.model.reference.TransactionStatusType;

public interface TransactionRepository {

	Integer countByAddressId(Long addressId);

	BigDecimal estimateFeeByHexTxnAndFeeCoefficient(String currencyName, String hexTransaction, 
			BigDecimal feeCoefficient);

	Transaction findByNetworkUid(String networkUid);

	List<Transaction> findByWalletIdAndChainCode(Integer walletId, String chainCode);

	Transaction findOne(Long transactionId);

	TransactionStatusType findTransactionStatusTypeByCode(String code);

	Transaction saveAndFlush(Transaction transaction);

	String updateTransactionStatusTypesToCompleted(Short confirmationCount, Integer blockHeight, 
			Date blockTime, String chainCode);

	String updateTransactionStatusTypesToConfirmed(String networkUidsCsv, Integer blockHeight, 
			Date blockTime, String chainCode);

	String updateTransactionStatusTypesToDropped(Integer transactionTimeout, String chainCode);
}