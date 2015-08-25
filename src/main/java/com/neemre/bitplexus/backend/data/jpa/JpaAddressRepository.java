package com.neemre.bitplexus.backend.data.jpa;

import java.math.BigDecimal;
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
	Integer countByLabelAndWalletIdAndChainCode(String labelFragment, Integer walletId, 
			String chainCode);

	@Override
	@Query("SELECT ast FROM AddressStateType AS ast WHERE ast.code = :code")
	AddressStateType findAddressStateTypeByCode(@Param("code") String code);

	@Override
	@Query("SELECT a FROM Address AS a WHERE a.encodedForm = :encodedForm")
	Address findByEncodedForm(@Param("encodedForm") String encodedForm);

	@Override
	@Query("SELECT a FROM Address AS a INNER JOIN a.addressStateType AS ast INNER JOIN a.wallet AS w "
			+ "INNER JOIN a.addressType AS at INNER JOIN at.chain AS ch WHERE ch.code = :chainCode "
			+ "ORDER BY w.walletId, ast.addressStateTypeId, a.label")
	List<Address> findByNonNullWalletIdAndChainCode(@Param("chainCode") String chainCode);

	@Override
	@Query("SELECT a FROM Address AS a INNER JOIN a.addressStateType AS ast "
			+ "INNER JOIN a.addressType AS at INNER JOIN at.chain AS ch WHERE ch.code = :chainCode "
			+ "ORDER BY at.addressTypeId, a.encodedForm")
	List<Address> findByNullWalletIdAndChainCode(@Param("chainCode") String chainCode);

	@Override
	@Procedure("f_get_transaction_addresses")
	String findByTransactionNetworkUid(String networkUid);

	@Override
	@Query("SELECT a FROM Address AS a INNER JOIN a.addressStateType AS ast INNER JOIN a.wallet AS w "
			+ "INNER JOIN a.addressType AS at INNER JOIN at.chain AS ch WHERE w.walletId = :walletId "
			+ "AND ch.code = :chainCode ORDER BY ast.addressStateTypeId, a.label")
	List<Address> findByWalletIdAndChainCode(@Param("walletId") Integer walletId, 
			@Param("chainCode") String chainCode);

	@Override
	@Procedure("f_get_wallet_subbalance")
	BigDecimal sumBalanceByWalletIdAndChainCode(Integer walletId, String chainCode);
}