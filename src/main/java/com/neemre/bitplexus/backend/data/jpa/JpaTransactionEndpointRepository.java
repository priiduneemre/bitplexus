package com.neemre.bitplexus.backend.data.jpa;

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
}