package com.neemre.bitplexus.backend.data;

import java.math.BigDecimal;
import java.util.List;

import com.neemre.bitplexus.backend.model.Address;
import com.neemre.bitplexus.backend.model.reference.AddressStateType;

public interface AddressRepository {

	Integer countByLabelAndWalletIdAndChainCode(String labelFragment, Integer walletId, 
			String chainCode);
	
	AddressStateType findAddressStateTypeByCode(String code);

	List<Address> findByWalletIdAndChainCode(Integer walletId, String chainCode);
	
	Address findOne(Long addressId);
	
	Address saveAndFlush(Address address);
	
	BigDecimal sumBalanceByWalletIdAndChainCode(Integer walletId, String chainCode);
}