package com.neemre.bitplexus.backend.data;

import java.util.List;

import com.neemre.bitplexus.backend.model.Wallet;
import com.neemre.bitplexus.backend.model.reference.WalletStateType;

public interface WalletRepository {
	
	List<Wallet> findByCustomerUsername(String username);
	
	Wallet findOne(Integer walletId);
	
	WalletStateType findWalletStateTypeByCode(String code);
	
	Wallet saveAndFlush(Wallet wallet);
}