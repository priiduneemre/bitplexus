package com.neemre.bitplexus.backend.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.neemre.bitplexus.backend.data.AddressRepository;
import com.neemre.bitplexus.backend.data.CustomerRepository;
import com.neemre.bitplexus.backend.data.WalletRepository;
import com.neemre.bitplexus.backend.model.Wallet;
import com.neemre.bitplexus.backend.model.enums.WalletStateTypes;
import com.neemre.bitplexus.backend.service.ChainService;
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.backend.service.WalletService;
import com.neemre.bitplexus.common.PropertyKeys;
import com.neemre.bitplexus.common.dto.ChainDto;
import com.neemre.bitplexus.common.dto.WalletDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private WalletRepository walletRepository;

	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@Autowired
	private ChainService chainService;
	@Autowired
	private TransactionService transactionService;


	@Transactional(readOnly = true)
	@Override
	public Boolean checkSubwalletSufficientFunds(BigDecimal requiredAmount, Integer walletId, 
			String chainCode) {
		BigDecimal subwalletBalance = addressRepository.sumBalanceByWalletIdAndChainCode(walletId, 
				chainCode);
		BigDecimal fee = transactionService.findTransactionOptimalFee(requiredAmount, walletId,
				chainCode);
		if (subwalletBalance.compareTo(requiredAmount.add(fee)) >= 0) {
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public Boolean checkWalletOwnership(Integer walletId, String username) {
		Wallet wallet = walletRepository.findOne(walletId);
		if (wallet.getCustomer().getUsername().equals(username)) {
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public Integer countCustomerWalletsByName(String nameFragment, Integer customerId) {
		return walletRepository.countByNameAndCustomerId(nameFragment, customerId);
	}

	@Transactional
	@Override
	public WalletDto createNewWallet(WalletDto walletDto) {
		Wallet wallet = dtoAssembler.disassemble(walletDto, WalletDto.class, Wallet.class);
		wallet.setCustomer(customerRepository.findByUsername(walletDto.getUsername()));
		wallet.setWalletStateType(walletRepository.findWalletStateTypeByCode(
				WalletStateTypes.CREATED.name()));
		if ((wallet.getName() == null) || wallet.getName().isEmpty()) {
			wallet.setName(getNewWalletDefaultName(wallet.getCustomer().getCustomerId()));
		}
		Wallet createdWallet = walletRepository.saveAndFlush(wallet);
		return dtoAssembler.assemble(createdWallet, Wallet.class, WalletDto.class);
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String getNewWalletDefaultName(Integer customerId) {
		String namePrefix = messageSource.getMessage(PropertyKeys.WALLET_NAME_DEFAULT.getValue(),
				null, LocaleContextHolder.getLocale());
		int nameIndex = walletRepository.countByNameAndCustomerId(namePrefix, customerId) + 1;
		if (nameIndex == 1) {
			return namePrefix;
		} else {
			return String.format("%s #%s", namePrefix, nameIndex);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public BigDecimal findSubwalletBalance(Integer walletId, String chainCode) {
		return addressRepository.sumBalanceByWalletIdAndChainCode(walletId, chainCode);
	}

	@Transactional(readOnly = true)
	@Override
	public BigDecimal findWalletBalance(Integer walletId) {
		BigDecimal balance = BigDecimal.ZERO;
		List<ChainDto> chains = chainService.findChainsByOperationality(true);
		for (ChainDto chain : chains) {
			BigDecimal subwalletBalance = addressRepository.sumBalanceByWalletIdAndChainCode(walletId,
					chain.getCode());
			BigDecimal unitPrice = chainService.findChainUnitPrice(chain.getCode());
			balance = balance.add(subwalletBalance.multiply(unitPrice));
		}
		return balance;
	}

	@Transactional(readOnly = true)
	@Override
	public WalletDto findWalletById(Integer walletId) {
		Wallet wallet = walletRepository.findOne(walletId);
		return dtoAssembler.assemble(wallet, Wallet.class, WalletDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<WalletDto> findWalletsByCustomerUsername(String username) {
		List<Wallet> wallets = walletRepository.findByCustomerUsername(username);
		return dtoAssembler.assemble(wallets, Wallet.class, WalletDto.class);
	}

	@Transactional
	@Override
	public WalletDto updateWallet(WalletDto walletDto) {
		Wallet wallet = walletRepository.findOne(walletDto.getWalletId());
		wallet.setName(walletDto.getName());
		Wallet updatedWallet = walletRepository.saveAndFlush(wallet);
		return dtoAssembler.assemble(updatedWallet, Wallet.class, WalletDto.class);
	}

	@Transactional
	@Override
	public WalletDto updateWalletState(WalletDto walletDto) {
		Wallet wallet = walletRepository.findOne(walletDto.getWalletId());
		wallet.setWalletStateType(walletRepository.findWalletStateTypeByCode(walletDto
				.getWalletStateType().getCode()));
		Wallet updatedWallet = walletRepository.saveAndFlush(wallet);
		return dtoAssembler.assemble(updatedWallet, Wallet.class, WalletDto.class);
	}
}