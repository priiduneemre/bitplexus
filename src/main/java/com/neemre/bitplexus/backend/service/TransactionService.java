package com.neemre.bitplexus.backend.service;

import java.util.List;

import com.neemre.bitplexus.common.dto.TransactionDto;

public interface TransactionService {
	
	List<TransactionDto> findSubwalletTransactions(Integer walletId, String chainCode);
	
	TransactionDto findTransactionById(Long transactionId);
}