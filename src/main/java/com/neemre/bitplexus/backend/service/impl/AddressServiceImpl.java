package com.neemre.bitplexus.backend.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.neemre.bitplexus.backend.crypto.BitcoinWrapperException;
import com.neemre.bitplexus.backend.crypto.LitecoinWrapperException;
import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.crypto.NodeWrapperResolver;
import com.neemre.bitplexus.backend.data.AddressRepository;
import com.neemre.bitplexus.backend.data.AddressTypeRepository;
import com.neemre.bitplexus.backend.data.WalletRepository;
import com.neemre.bitplexus.backend.model.Address;
import com.neemre.bitplexus.backend.model.AddressType;
import com.neemre.bitplexus.backend.model.Wallet;
import com.neemre.bitplexus.backend.model.enums.AddressStateTypes;
import com.neemre.bitplexus.backend.model.enums.WalletStateTypes;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.common.Constants;
import com.neemre.bitplexus.common.Defaults;
import com.neemre.bitplexus.common.Errors;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;
import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.ltcdcli4j.core.LitecoindException;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressTypeRepository addressTypeRepository;
	@Autowired
	private WalletRepository walletRepository;

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
	public AddressDto createNewExternalAddress(AddressDto addressDto, String chainCode) {
		Address address = dtoAssembler.disassemble(addressDto, AddressDto.class, Address.class);
		address.setEncodedForm(addressDto.getEncodedForm());
		address.setAddressType(new AddressType(addressTypeRepository.findIdByAddressAndChainCode(
				address.getEncodedForm(), chainCode), null, null, null, null, null, null, null, null));
		Address createdAddress = addressRepository.saveAndFlush(address);
		return dtoAssembler.assemble(createdAddress, Address.class, AddressDto.class);
	}

	@Transactional
	@Override
	public AddressDto createNewWalletAddress(AddressDto addressDto, String chainCode) 
			throws NodeWrapperException {
		Address address = dtoAssembler.disassemble(addressDto, AddressDto.class, Address.class);
		if (clientResolver.getBtcdClient(chainCode) != null) {
			address.setEncodedForm(getBtcNewAddress(chainCode));
		} else if (clientResolver.getLtcdClient(chainCode) != null) {
			address.setEncodedForm(getLtcNewAddress(chainCode));
		}
		Wallet relatedWallet = walletRepository.findOne(addressDto.getWalletId());
		if (relatedWallet.getWalletStateType().getCode().equals(WalletStateTypes.CREATED.name())) {
			relatedWallet.setWalletStateType(walletRepository.findWalletStateTypeByCode(
					WalletStateTypes.ACTIVE.name()));
			relatedWallet = walletRepository.saveAndFlush(relatedWallet);
		}
		address.setWallet(relatedWallet);
		address.setAddressType(new AddressType(addressTypeRepository.findIdByAddressAndChainCode(
				address.getEncodedForm(), chainCode), null, null, null, null, null, null, null, null));
		address.setBalance(BigDecimal.ZERO);
		Address createdAddress = addressRepository.saveAndFlush(address);
		return dtoAssembler.assemble(createdAddress, Address.class, AddressDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public AddressDto findAddressByEncodedForm(String encodedForm) {
		Address address = addressRepository.findByEncodedForm(encodedForm);
		return dtoAssembler.assemble(address, Address.class, AddressDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public AddressDto findAddressById(Long addressId) {
		Address address = addressRepository.findOne(addressId);
		return dtoAssembler.assemble(address, Address.class, AddressDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<String> findAddressesByTransactionNetworkUid(String networkUid) {
		String encodedFormsCsv = addressRepository.findByTransactionNetworkUid(networkUid);
		return Lists.newArrayList(Splitter.on(Constants.STRING_COMMA).omitEmptyStrings().trimResults()
				.split(MoreObjects.firstNonNull(encodedFormsCsv, Constants.STRING_EMPTY)
						.replaceAll(Constants.STRING_NULL, Constants.STRING_EMPTY)));
	}

	@Transactional(readOnly = true)
	@Override
	public List<AddressDto> findExternalAddressesByChainCode(String chainCode) {
		List<Address> addresses = addressRepository.findByNullWalletIdAndChainCode(chainCode);
		return dtoAssembler.assemble(addresses, Address.class, AddressDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<AddressDto> findSubwalletAddresses(Integer walletId, String chainCode) {
		List<Address> addresses = addressRepository.findByWalletIdAndChainCode(walletId, chainCode);
		return dtoAssembler.assemble(addresses, Address.class, AddressDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<AddressDto> findWalletAddressesByChainCode(String chainCode) {
		List<Address> addresses = addressRepository.findByNonNullWalletIdAndChainCode(chainCode);
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
		BigDecimal updatedBalance = null;
		if (clientResolver.getBtcdClient(chainCode) != null) {
			updatedBalance = getBtcAddressUnspent(address.getEncodedForm(), chainCode);
		} else if (clientResolver.getLtcdClient(chainCode) != null) {
			updatedBalance = getLtcAddressUnspent(address.getEncodedForm(), chainCode);
		}
		if (address.getAddressStateType().getCode().equals(AddressStateTypes.ALLOCATED.name())) {
			if ((updatedBalance.compareTo(BigDecimal.ZERO) == 1)) {
				address.setAddressStateType(addressRepository.findAddressStateTypeByCode(
						AddressStateTypes.ACTIVE.name()));
			}
		}
		if (address.getAddressStateType().getCode().equals(AddressStateTypes.ACTIVE.name())) {
			if ((updatedBalance.compareTo(BigDecimal.ZERO) == 0)) {
				address.setAddressStateType(addressRepository.findAddressStateTypeByCode(
						AddressStateTypes.USED.name()));
			}
		}
		address.setBalance(updatedBalance);
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
			List<com.neemre.btcdcli4j.core.domain.Output> unspentOutputs = clientResolver
					.getBtcdClient(chainCode).listUnspent(Defaults.BTC_COMPLETED_CONF_COUNT,
							Integer.MAX_VALUE, Arrays.asList(encodedForm));
			BigDecimal balance = BigDecimal.ZERO;
			for (int i = 0; i < unspentOutputs.size(); i++) {
				balance = balance.add(unspentOutputs.get(i).getAmount());
			}
			return balance;
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private BigDecimal getLtcAddressUnspent(String encodedForm, String chainCode) 
			throws LitecoinWrapperException {
		try {
			List<com.neemre.ltcdcli4j.core.domain.Output> unspentOutputs = clientResolver
					.getLtcdClient(chainCode).listUnspent(Defaults.LTC_COMPLETED_CONF_COUNT, 
							Integer.MAX_VALUE, Arrays.asList(encodedForm));
			BigDecimal balance = BigDecimal.ZERO;
			for (int i = 0; i < unspentOutputs.size(); i++) {
				balance = balance.add(unspentOutputs.get(i).getAmount());
			}
			return balance;
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String getBtcNewAddress(String chainCode) throws BitcoinWrapperException {
		try {
			return clientResolver.getBtcdClient(chainCode).getNewAddress();
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String getLtcNewAddress(String chainCode) throws LitecoinWrapperException {
		try {
			return clientResolver.getLtcdClient(chainCode).getNewAddress();
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}
}