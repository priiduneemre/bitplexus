package com.neemre.bitplexus.backend.data.jpa;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.TransactionEndpointRepository;
import com.neemre.bitplexus.backend.model.TransactionEndpoint;
import com.neemre.bitplexus.backend.model.reference.TransactionEndpointType;

public interface JpaTransactionEndpointRepository extends TransactionEndpointRepository, 
		JpaRepository<TransactionEndpoint, Long> {

	@Override
	@Query("SELECT tet FROM TransactionEndpointType AS tet WHERE tet.code = :code")
	TransactionEndpointType findTransactionEndpointTypeByCode(@Param("code") String code);

	@Override
	@Query("SELECT sum(te.amount) FROM TransactionEndpoint AS te "
			+ "INNER JOIN te.address AS a INNER JOIN te.transactionEndpointType AS tet "
			+ "WHERE a.addressId = :addressId AND tet.code = :txnEndpointTypeCode")
	BigDecimal sumAmountByAddressIdAndTxnEndpointTypeCode(@Param("addressId") Long addressId, 
			@Param("txnEndpointTypeCode") String txnEndpointTypeCode);
}