package com.neemre.bitplexus.backend.service;

import java.math.BigDecimal;
import java.util.List;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.common.dto.AddressDto;

public interface AddressService {

	Boolean checkAddressOwnership(Long addressId, String username);

	Integer countAddressTransactions(Long addressId);

	Integer countSubwalletAddressesByLabel(String labelFragment, Integer walletId, String chainCode);

	AddressDto createNewExternalAddress(AddressDto addressDto, String chainCode);

	AddressDto createNewWalletAddress(AddressDto addressDto, Boolean isChange, String chainCode) 
			throws NodeWrapperException;

	AddressDto findAddressByEncodedForm(String encodedForm);

	AddressDto findAddressById(Long addressId);

	List<String> findAddressesByTransactionNetworkUid(String networkUid);

	BigDecimal findAddressTotalReceived(Long addressId);

	BigDecimal findAddressTotalSent(Long addressId);

	List<AddressDto> findExternalAddressesByChainCode(String chainCode);

	List<AddressDto> findSubwalletAddresses(Integer walletId, String chainCode);

	List<AddressDto> findWalletAddressesByChainCode(String chainCode);

	AddressDto updateAddress(AddressDto addressDto);

	AddressDto updateAddressBalance(Long addressId) throws NodeWrapperException;

	AddressDto updateAddressState(AddressDto addressDto);
}