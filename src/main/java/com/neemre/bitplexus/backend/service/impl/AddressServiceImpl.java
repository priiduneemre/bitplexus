package com.neemre.bitplexus.backend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neemre.bitplexus.backend.crypto.NodeWrapperResolver;
import com.neemre.bitplexus.backend.data.AddressRepository;
import com.neemre.bitplexus.backend.model.Address;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	@Autowired
	private NodeWrapperResolver clientResolver;

	
	@Transactional
	@Override
	public AddressDto createNewAddress(AddressDto addressDto) {
		Address address = dtoAssembler.disassemble(addressDto, AddressDto.class, Address.class);
		
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<AddressDto> findSubwalletAddresses(Integer walletId, String chainCode) {
		List<Address> addresses = addressRepository.findByWalletIdAndChainCode(walletId, chainCode);
		return dtoAssembler.assemble(addresses, Address.class, AddressDto.class);
	}

	@Transactional
	@Override
	public AddressDto updateAddress(AddressDto addressDto) {
		return null;
	}

	@Transactional
	@Override
	public AddressDto updateAddressState(AddressDto addressDto) {
		return null;
	}
}