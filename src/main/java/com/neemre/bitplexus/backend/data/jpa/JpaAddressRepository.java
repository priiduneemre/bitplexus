package com.neemre.bitplexus.backend.data.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.AddressRepository;
import com.neemre.bitplexus.backend.model.Address;
import com.neemre.bitplexus.backend.model.reference.AddressStateType;

public interface JpaAddressRepository extends AddressRepository, JpaRepository<Address, Long> {

	@Override
	@Procedure("f_count_addresses_by_label")
	Integer countByLabelAndWalletIdAndChainCode(@Param("in_label_fragment") String labelFragment, 
			@Param("in_wallet_id") Integer walletId, @Param("in_chain_code") String chainCode);
	
	@Override
	@Query("SELECT ast FROM AddressStateType AS ast WHERE ast.code = :code")
	AddressStateType findAddressStateTypeByCode(@Param("code") String code);
	
	@Override
	@Query("SELECT a FROM Address AS a INNER JOIN a.addressStateType AS ast INNER JOIN a.wallet AS w "
			+ "INNER JOIN a.addressType AS at INNER JOIN at.chain AS ch WHERE w.walletId = :walletId "
			+ "AND ch.code = :chainCode ORDER BY ast.addressStateTypeId, a.label")
	List<Address> findByWalletIdAndChainCode(@Param("walletId") Integer walletId, 
			@Param("chainCode") String chainCode);
}