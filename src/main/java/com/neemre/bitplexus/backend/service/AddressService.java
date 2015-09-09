package com.neemre.bitplexus.backend.service;

import java.util.List;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.common.dto.AddressDto;

public interface AddressService {

	Integer countSubwalletAddressesByLabel(String labelFragment, Integer walletId, String chainCode);

	AddressDto createNewExternalAddress(AddressDto addressDto, String chainCode);

	AddressDto createNewWalletAddress(AddressDto addressDto, Boolean isChange, String chainCode) 
			throws NodeWrapperException;

	AddressDto findAddressByEncodedForm(String encodedForm);

	AddressDto findAddressById(Long addressId);

	List<String> findAddressesByTransactionNetworkUid(String networkUid);

	List<AddressDto> findExternalAddressesByChainCode(String chainCode);

	List<AddressDto> findSubwalletAddresses(Integer walletId, String chainCode);

	List<AddressDto> findWalletAddressesByChainCode(String chainCode);

	AddressDto updateAddress(AddressDto addressDto);

	AddressDto updateAddressBalance(Long addressId) throws NodeWrapperException;

	AddressDto updateAddressState(AddressDto addressDto);
}