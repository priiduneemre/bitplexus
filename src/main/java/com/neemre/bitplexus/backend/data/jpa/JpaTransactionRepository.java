package com.neemre.bitplexus.backend.data.jpa;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.TransactionRepository;
import com.neemre.bitplexus.backend.model.Transaction;
import com.neemre.bitplexus.backend.model.reference.TransactionStatusType;

public interface JpaTransactionRepository extends TransactionRepository, 
		JpaRepository<Transaction, Long> {

	@Override
	@Procedure("f_estimate_transaction_fee")
	BigDecimal estimateFeeByHexTxAndFeeCoefficient(String currencyName, String hexTransaction, 
			BigDecimal feeCoefficient);
	
	@Override
	@Query("SELECT t FROM Transaction AS t WHERE t.networkUid = :networkUid")
	Transaction findByNetworkUid(@Param("networkUid") String networkUid);
	
	@Override
	@Query("SELECT t FROM Transaction AS t INNER JOIN t.transactionEndpoints AS te "
			+ "INNER JOIN te.address AS a INNER JOIN a.wallet AS w INNER JOIN a.addressType AS at "
			+ "INNER JOIN at.chain AS ch WHERE w.walletId = :walletId AND ch.code = :chainCode "
			+ "ORDER BY t.receivedAt DESC")
	List<Transaction> findByWalletIdAndChainCode(@Param("walletId") Integer walletId, 
			@Param("chainCode") String chainCode);
	
	@Override
	@Query("SELECT tst FROM TransactionStatusType AS tst WHERE tst.code = :code")
	TransactionStatusType findTransactionStatusTypeByCode(@Param("code") String code);
}