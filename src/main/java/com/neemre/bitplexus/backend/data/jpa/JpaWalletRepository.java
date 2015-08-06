package com.neemre.bitplexus.backend.data.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.WalletRepository;
import com.neemre.bitplexus.backend.model.Wallet;
import com.neemre.bitplexus.backend.model.reference.WalletStateType;

public interface JpaWalletRepository extends WalletRepository, JpaRepository<Wallet, Integer> {

	@Override
	@Query("SELECT w FROM Wallet AS w INNER JOIN w.walletStateType AS wst INNER JOIN w.customer AS c "
			+ "WHERE c.username = :username ORDER BY wst.walletStateTypeId, w.name")
	List<Wallet> findByCustomerUsername(@Param("username") String username);
	
	@Override
	@Query("SELECT wst FROM WalletStateType AS wst WHERE wst.code = :code")
	WalletStateType findWalletStateTypeByCode(@Param("code") String code);
}