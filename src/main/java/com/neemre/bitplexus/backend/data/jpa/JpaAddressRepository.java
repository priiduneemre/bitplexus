package com.neemre.bitplexus.backend.data.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.AddressRepository;
import com.neemre.bitplexus.backend.model.Address;

public interface JpaAddressRepository extends AddressRepository, JpaRepository<Address, Long> {

	@Override
	@Query("SELECT a FROM Address AS a INNER JOIN a.addressStateType AS ast INNER JOIN a.wallet AS w "
			+ "INNER JOIN a.addressType AS at INNER JOIN at.chain AS ch WHERE w.walletId = :walletId "
			+ "AND ch.code = :code ORDER BY ast.addressStateTypeId, a.label")
	List<Address> findByWalletIdAndChainCode(@Param("walletId") Integer walletId, 
			@Param("code") String code);
}