package com.neemre.bitplexus.backend.data;

public interface AddressTypeRepository {

	Short findIdByAddressAndChainCode(String encodedForm, String chainCode);
}