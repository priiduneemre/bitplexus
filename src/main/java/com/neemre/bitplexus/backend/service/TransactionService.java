package com.neemre.bitplexus.backend.service;

import java.math.BigDecimal;
import java.util.List;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.common.dto.TransactionDto;
import com.neemre.bitplexus.common.dto.virtual.PaymentDetailsDto;

public interface TransactionService {
	
	List<TransactionDto> findSubwalletTransactions(Integer walletId, String chainCode);
	
	TransactionDto findTransactionById(Long transactionId);
	
	BigDecimal findTransactionMinimumFee(String chainCode);
	
	BigDecimal findTransactionOptimalFee(String hexTransaction, String chainCode);
	
}