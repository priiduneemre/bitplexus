package com.neemre.bitplexus.backend.service;

import java.util.List;

import com.neemre.bitplexus.common.dto.AddressDto;

public interface AddressService {

	AddressDto createNewAddress(AddressDto addressDto);
	
	List<AddressDto> findSubwalletAddresses(Integer walletId, String chainCode);
	
	AddressDto updateAddress(AddressDto addressDto);
	
	AddressDto updateAddressState(AddressDto addressDto);
}