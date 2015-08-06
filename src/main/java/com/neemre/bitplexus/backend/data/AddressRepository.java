package com.neemre.bitplexus.backend.data;

import java.util.List;

import com.neemre.bitplexus.backend.model.Address;

public interface AddressRepository {

	List<Address> findByWalletIdAndChainCode(Integer walletId, String code);
	
	Address saveAndFlush(Address address);
}