package com.neemre.bitplexus.backend.service;

import java.math.BigDecimal;
import java.util.List;

import com.neemre.bitplexus.common.dto.WalletDto;

public interface WalletService {

	Boolean checkSubwalletSufficientFunds(BigDecimal requiredAmount, Integer walletId, 
			String chainCode);

	Boolean checkWalletOwnership(Integer walletId, String username);

	Integer countCustomerWalletsByName(String nameFragment, Integer customerId);

	WalletDto createNewWallet(WalletDto walletDto);

	BigDecimal findSubwalletBalance(Integer walletId, String chainCode);

	BigDecimal findWalletBalance(Integer walletId);

	WalletDto findWalletById(Integer walletId);

	List<WalletDto> findWalletsByCustomerUsername(String username);

	WalletDto updateWallet(WalletDto walletDto);

	WalletDto updateWalletState(WalletDto walletDto);
}