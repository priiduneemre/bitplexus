package com.neemre.bitplexus.backend.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.common.dto.TransactionDto;
import com.neemre.bitplexus.common.dto.virtual.PaymentDetailsDto;

public interface TransactionService {

	List<String> completeTransactions(Integer blockHeight, Date blockTime, String chainCode);

	List<String> confirmTransactions(List<String> networkUids, Integer blockHeight, Date blockTime, 
			String chainCode);

	List<String> dropTransactions(String chainCode);

	List<TransactionDto> findSubwalletTransactions(Integer walletId, String chainCode);

	TransactionDto findTransactionById(Long transactionId);

	BigDecimal findTransactionMinimumFee(String chainCode);

	BigDecimal findTransactionOptimalFee(String hexTransaction, String chainCode);
}