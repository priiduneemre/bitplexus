package com.neemre.bitplexus.backend.service;

import java.util.List;

import com.neemre.bitplexus.common.dto.WalletDto;

public interface WalletService {

	WalletDto createNewWallet(WalletDto walletDto);
	
	WalletDto findWalletById(Integer walletId);
	
	List<WalletDto> findWalletsByCustomerUsername(String username);

	WalletDto updateWallet(WalletDto walletDto);

	WalletDto updateWalletState(WalletDto walletDto);
}