package com.neemre.bitplexus.backend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neemre.bitplexus.backend.crypto.NodeWrapperResolver;
import com.neemre.bitplexus.backend.data.TransactionEndpointRepository;
import com.neemre.bitplexus.backend.data.TransactionRepository;
import com.neemre.bitplexus.backend.model.Transaction;
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.common.dto.TransactionDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private TransactionEndpointRepository transactionEndpointRepository;
	
	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	@Autowired
	private NodeWrapperResolver clientResolver;

	
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
}