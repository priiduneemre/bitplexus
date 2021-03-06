package com.neemre.bitplexus.backend.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.crypto.adapter.NodeClientAdapter;
import com.neemre.bitplexus.backend.data.AddressRepository;
import com.neemre.bitplexus.backend.data.AddressTypeRepository;
import com.neemre.bitplexus.backend.data.TransactionEndpointRepository;
import com.neemre.bitplexus.backend.data.TransactionRepository;
import com.neemre.bitplexus.backend.data.WalletRepository;
import com.neemre.bitplexus.backend.model.Address;
import com.neemre.bitplexus.backend.model.AddressType;
import com.neemre.bitplexus.backend.model.Wallet;
import com.neemre.bitplexus.backend.model.enums.AddressStateTypes;
import com.neemre.bitplexus.backend.model.enums.TransactionEndpointTypes;
import com.neemre.bitplexus.backend.model.enums.WalletStateTypes;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.common.Constants;
import com.neemre.bitplexus.common.PropertyKeys;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressTypeRepository addressTypeRepository;
	@Autowired
	private TransactionEndpointRepository transactionEndpointRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private WalletRepository walletRepository;

	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	@Autowired
	private NodeClientAdapter nodeClient;


	@Transactional(readOnly = true)
	@Override
	public Boolean checkAddressOwnership(Long addressId, String username) {
		Address address = addressRepository.findOne(addressId);
		if (address.getWallet().getCustomer().getUsername().equals(username)) {
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public Integer countAddressTransactions(Long addressId) {
		return transactionRepository.countByAddressId(addressId);
	}

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
	public AddressDto createNewWalletAddress(AddressDto addressDto, Boolean isChange, 
			String chainCode) throws NodeWrapperException {
		Address address = dtoAssembler.disassemble(addressDto, AddressDto.class, Address.class);
		if (nodeClient.isOperationalBtcChain(chainCode)) {
			address.setEncodedForm(nodeClient.getBtcNewAddress(chainCode));
		} else if (nodeClient.isOperationalLtcChain(chainCode)) {
			address.setEncodedForm(nodeClient.getLtcNewAddress(chainCode));
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
		if ((address.getLabel() == null) || (address.getLabel().isEmpty())) {
			address.setLabel(getNewAddressDefaultLabel(isChange, address.getWallet().getWalletId(), 
					chainCode));
		}
		address.setBalance(BigDecimal.ZERO);
		Address createdAddress = addressRepository.saveAndFlush(address);
		return dtoAssembler.assemble(createdAddress, Address.class, AddressDto.class);
	}

	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	private String getNewAddressDefaultLabel(Boolean isChange, Integer walletId, String chainCode) {
		String labelPrefix = Constants.STRING_EMPTY;
		if (isChange) {
			labelPrefix = messageSource.getMessage(PropertyKeys.ADDRESS_CHANGE_LABEL_DEFAULT
					.getValue(), null, LocaleContextHolder.getLocale());
		} else {
			labelPrefix = messageSource.getMessage(PropertyKeys.ADDRESS_REGULAR_LABEL_DEFAULT
					.getValue(), null, LocaleContextHolder.getLocale());
		}
		int labelIndex = addressRepository.countByLabelAndWalletIdAndChainCode(labelPrefix, walletId,
				chainCode) + 1;
		if (labelIndex == 1) {
			return labelPrefix;
		} else {
			return String.format("%s #%s", labelPrefix, labelIndex);
		}
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
	public BigDecimal findAddressTotalReceived(Long addressId) {
		BigDecimal mainOutputSum = MoreObjects.firstNonNull(
				transactionEndpointRepository.sumAmountByAddressIdAndTxnEndpointTypeCode(addressId, 
						TransactionEndpointTypes.OUTPUT_MAIN.name()), BigDecimal.ZERO);
		BigDecimal changeOutputSum = MoreObjects.firstNonNull(
				transactionEndpointRepository.sumAmountByAddressIdAndTxnEndpointTypeCode(addressId, 
						TransactionEndpointTypes.OUTPUT_CHANGE.name()), BigDecimal.ZERO);
		BigDecimal outputSum = mainOutputSum.add(changeOutputSum);
		return outputSum;
	}

	@Transactional(readOnly = true)
	@Override
	public BigDecimal findAddressTotalSent(Long addressId) {
		BigDecimal inputSum = MoreObjects.firstNonNull(
				transactionEndpointRepository.sumAmountByAddressIdAndTxnEndpointTypeCode(addressId, 
						TransactionEndpointTypes.INPUT.name()), BigDecimal.ZERO);
		return inputSum;
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
		if (address.getWallet() == null) {
			return dtoAssembler.assemble(address, Address.class, AddressDto.class);
		}
		String chainCode = address.getAddressType().getChain().getCode();
		BigDecimal updatedBalance = null;
		if (nodeClient.isOperationalBtcChain(chainCode)) {
			updatedBalance = nodeClient.getBtcAddressBalance(address.getEncodedForm(), chainCode);
		} else if (nodeClient.isOperationalLtcChain(chainCode)) {
			updatedBalance = nodeClient.getLtcAddressBalance(address.getEncodedForm(), chainCode);
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
}