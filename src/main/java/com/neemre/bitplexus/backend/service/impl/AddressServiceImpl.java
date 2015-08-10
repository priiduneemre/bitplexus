package com.neemre.bitplexus.backend.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.neemre.bitplexus.backend.crypto.BitcoinWrapperException;
import com.neemre.bitplexus.backend.crypto.LitecoinWrapperException;
import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.crypto.NodeWrapperResolver;
import com.neemre.bitplexus.backend.data.AddressRepository;
import com.neemre.bitplexus.backend.data.AddressTypeRepository;
import com.neemre.bitplexus.backend.model.Address;
import com.neemre.bitplexus.backend.model.reference.enums.AddressStateTypes;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.common.Defaults;
import com.neemre.bitplexus.common.Errors;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;
import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.domain.Output;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressTypeRepository addressTypeRepository;

	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	@Autowired
	private NodeWrapperResolver clientResolver;

	
	@Transactional(readOnly = true)
	@Override
	public Integer countSubwalletAddressesByLabel(String labelFragment, Integer walletId, 
			String chainCode) {
		return addressRepository.countByLabelAndWalletIdAndChainCode(labelFragment, walletId, 
				chainCode);
	}

	@Transactional
	@Override
	public AddressDto createNewAddress(AddressDto addressDto, String chainCode) 
			throws NodeWrapperException {
		Address address = dtoAssembler.disassemble(addressDto, AddressDto.class, Address.class);
		if (clientResolver.getBtcdClient(chainCode) != null) {
			address.setEncodedForm(getNewBtcAddress(chainCode));
		} else if (clientResolver.getLtcdClient(chainCode) != null) {
			address.setEncodedForm(getNewLtcAddress(chainCode));
		}
		address.getWallet().setWalletId(addressDto.getWalletId());
		address.getAddressType().setAddressTypeId(addressTypeRepository.findIdByAddressAndChainCode(
				address.getEncodedForm(), chainCode));
		address.setAddressStateType(addressRepository.findAddressStateTypeByCode(
				AddressStateTypes.ALLOCATED.name()));
		address.setBalance(BigDecimal.ZERO);
		Address createdAddress = addressRepository.saveAndFlush(address);
		return dtoAssembler.assemble(createdAddress, Address.class, AddressDto.class);
	}
	
	@Transactional(readOnly = true)
	@Override
	public AddressDto findAddressById(Long addressId) {
		Address address = addressRepository.findOne(addressId);
		return dtoAssembler.assemble(address, Address.class, AddressDto.class);
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
		Address address = addressRepository.findOne(addressDto.getAddressId());
		address.setLabel(addressDto.getLabel());
		Address updatedAddress = addressRepository.saveAndFlush(address);
		return dtoAssembler.assemble(updatedAddress, Address.class, AddressDto.class);
	}

	@Transactional
	@Override
	public AddressDto updateAddressBalance(Long addressId) throws NodeWrapperException {
		Address address = addressRepository.findOne(addressId);
		String chainCode = address.getAddressType().getChain().getCode();
		if (clientResolver.getBtcdClient(chainCode) != null) {
			address.setBalance(getBtcAddressUnspent(address.getEncodedForm(), chainCode));
		} else if (clientResolver.getLtcdClient(chainCode) != null) {
			address.setBalance(getLtcAddressUnspent(address.getEncodedForm(), chainCode));
		}
		Address updatedAddress = addressRepository.saveAndFlush(address);
		return dtoAssembler.assemble(updatedAddress, Address.class, AddressDto.class);
	}

	@Transactional
	@Override
	public AddressDto updateAddressState(AddressDto addressDto) {
		Address address = addressRepository.findOne(addressDto.getAddressId());
		address.setAddressStateType(addressRepository.findAddressStateTypeByCode(addressDto
				.getAddressStateType().getCode()));
		Address updatedAddress = addressRepository.saveAndFlush(address);
		return dtoAssembler.assemble(updatedAddress, Address.class, AddressDto.class);
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private BigDecimal getBtcAddressUnspent(String encodedForm, String chainCode) 
			throws BitcoinWrapperException {
		try {
			List<Output> unspentOutputs = clientResolver.getBtcdClient(chainCode).listUnspent(
					Defaults.UNCONFIRMED_CONF_COUNT, Integer.MAX_VALUE, Arrays.asList(encodedForm));
			BigDecimal balance = BigDecimal.ZERO;
			for (Output output : unspentOutputs) {
				balance = balance.add(output.getAmount());
			}
			return balance;
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private BigDecimal getLtcAddressUnspent(String encodedForm, String chainCode) 
			throws LitecoinWrapperException {
		try {
			List<Output> unspentOutputs = clientResolver.getLtcdClient(chainCode).listUnspent(
					Defaults.UNCONFIRMED_CONF_COUNT, Integer.MAX_VALUE, Arrays.asList(encodedForm));
			BigDecimal balance = BigDecimal.ZERO;
			for (Output output : unspentOutputs) {
				balance = balance.add(output.getAmount());
			}
			return balance;
		} catch (BitcoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String getNewBtcAddress(String chainCode) throws BitcoinWrapperException {
		try {
			return clientResolver.getBtcdClient(chainCode).getNewAddress();
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String getNewLtcAddress(String chainCode) throws LitecoinWrapperException {
		try {
			return clientResolver.getLtcdClient(chainCode).getNewAddress();
		} catch (BitcoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}
}