package com.neemre.bitplexus.backend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neemre.bitplexus.backend.data.CustomerRepository;
import com.neemre.bitplexus.backend.data.WalletRepository;
import com.neemre.bitplexus.backend.model.Wallet;
import com.neemre.bitplexus.backend.model.reference.enums.WalletStateTypes;
import com.neemre.bitplexus.backend.service.WalletService;
import com.neemre.bitplexus.common.dto.WalletDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	
	
	@Transactional
	@Override
	public WalletDto createNewWallet(WalletDto walletDto) {
		Wallet wallet = dtoAssembler.disassemble(walletDto, WalletDto.class, Wallet.class);
		wallet.setCustomer(customerRepository.findByUsername(walletDto.getUsername()));
		wallet.setWalletStateType(walletRepository.findWalletStateTypeByCode(
				WalletStateTypes.CREATED.name()));
		Wallet createdWallet = walletRepository.saveAndFlush(wallet);
		return dtoAssembler.assemble(createdWallet, Wallet.class, WalletDto.class);
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