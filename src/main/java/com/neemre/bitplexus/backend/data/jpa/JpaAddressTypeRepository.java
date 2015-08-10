package com.neemre.bitplexus.backend.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.AddressTypeRepository;
import com.neemre.bitplexus.backend.model.AddressType;

public interface JpaAddressTypeRepository extends AddressTypeRepository, 
		JpaRepository<AddressType, Short> {

	@Override
	@Procedure("f_get_address_type_id")
	Short findIdByAddressAndChainCode(@Param("in_address") String encodedForm, 
			@Param("in_chain_code") String chainCode);
}