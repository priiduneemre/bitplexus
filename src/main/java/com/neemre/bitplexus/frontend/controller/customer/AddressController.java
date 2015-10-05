package com.neemre.bitplexus.frontend.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.model.enums.AddressStateTypes;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.backend.service.ChainService;
import com.neemre.bitplexus.backend.service.CurrencyService;
import com.neemre.bitplexus.backend.service.WalletService;
import com.neemre.bitplexus.common.Constants;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.AddressStateTypeDto;
import com.neemre.bitplexus.common.dto.CurrencyDto;
import com.neemre.bitplexus.common.util.ServletUtils;
import com.neemre.bitplexus.frontend.controller.misc.Views;

import static com.neemre.bitplexus.frontend.controller.misc.RequestAttributes.*;
import static com.neemre.bitplexus.frontend.controller.misc.RequestMappings.*;

@Controller
@RequestMapping(CUSTOMER_MAPPING)
public class AddressController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private ChainService chainService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private WalletService walletService;


	@RequestMapping(value = {VIEW_ALL_ADDRESSES_MAPPING_1, VIEW_ALL_ADDRESSES_MAPPING_2}, 
			method = RequestMethod.GET)
	public String viewAllAddresses(HttpServletRequest request, @PathVariable(WALLET_ID_ATTRIBUTE) 
			Integer walletId, @PathVariable(SUBWALLET_ID_ATTRIBUTE) Short subwalletId, ModelMap model) {
		if (!chainService.checkChainOperationality(subwalletId)) {
			return ServletUtils.getRedirectUrl(request, VIEW_ERROR_404_MAPPING);
		}
		if (!walletService.checkWalletOwnership(walletId, "rebel_sloth")) {
			return ServletUtils.getRedirectUrl(request, VIEW_ERROR_403_MAPPING);
		}
		String chainCode = chainService.findChainById(subwalletId).getCode();
		List<AddressDto> addresses = addressService.findSubwalletAddresses(walletId, chainCode);
		for (AddressDto address : addresses) {
			Long addressId = address.getAddressId();
			address.setTransactionCount(addressService.countAddressTransactions(addressId));
			address.setTotalReceived(addressService.findAddressTotalReceived(addressId));
			address.setTotalSent(addressService.findAddressTotalSent(addressId));
		}
		model.addAttribute(ADDRESSES_ATTRIBUTE, addresses);
		CurrencyDto currency = currencyService.findCurrencyByChainCode(chainCode);
		model.addAttribute(CURRENCY_ATTRIBUTE, currency);
		model.addAttribute(ADDRESS_ATTRIBUTE, new AddressDto());
		return Views.ALL_ADDRESSES_VIEW.getPath();
	}

	@RequestMapping(value = {PROCESS_NEW_ADDRESS_MAPPING}, method = RequestMethod.POST, 
			produces = {Constants.JSON_MEDIA_TYPE})
	@ResponseBody
	public ResponseEntity<AddressDto> processNewAddress(@PathVariable(SUBWALLET_ID_ATTRIBUTE) 
			Short subwalletId, @ModelAttribute(ADDRESS_ATTRIBUTE) AddressDto address) 
			throws NodeWrapperException {
		if (!chainService.checkChainOperationality(subwalletId)) {
			return new ResponseEntity<AddressDto>(HttpStatus.NOT_FOUND);
		}
		if (!walletService.checkWalletOwnership(address.getWalletId(), "rebel_sloth")) {
			return new ResponseEntity<AddressDto>(HttpStatus.FORBIDDEN);
		}
		String chainCode = chainService.findChainById(subwalletId).getCode();
		AddressDto createdAddress = addressService.createNewWalletAddress(address, false, chainCode);
		return new ResponseEntity<AddressDto>(createdAddress, HttpStatus.OK);
	}

	@RequestMapping(value = {PROCESS_DELETE_ADDRESS_MAPPING}, method = RequestMethod.POST, 
			produces = {Constants.JSON_MEDIA_TYPE})
	@ResponseBody
	public ResponseEntity<AddressDto> processDeleteAddress(@ModelAttribute(ADDRESS_ATTRIBUTE) 
			AddressDto address) {
		if (!walletService.checkWalletOwnership(address.getWalletId(), "rebel_sloth")) {
			return new ResponseEntity<AddressDto>(HttpStatus.FORBIDDEN);
		}
		if (!addressService.checkAddressOwnership(address.getAddressId(), "rebel_sloth")) {
			return new ResponseEntity<AddressDto>(HttpStatus.FORBIDDEN);
		}
		address.setAddressStateType(new AddressStateTypeDto(null, AddressStateTypes.DELETED.name(),
				null));
		AddressDto deletedAddress = addressService.updateAddressState(address);
		return new ResponseEntity<AddressDto>(deletedAddress, HttpStatus.OK);
	}

	@RequestMapping(value = {PROCESS_HIDE_ADDRESS_MAPPING}, method = RequestMethod.POST, 
			produces = {Constants.JSON_MEDIA_TYPE})
	@ResponseBody
	public ResponseEntity<AddressDto> processHideAddress(@ModelAttribute(ADDRESS_ATTRIBUTE) 
			AddressDto address) {
		if (!walletService.checkWalletOwnership(address.getWalletId(), "rebel_sloth")) {
			return new ResponseEntity<AddressDto>(HttpStatus.FORBIDDEN);
		}
		if (!addressService.checkAddressOwnership(address.getAddressId(), "rebel_sloth")) {
			return new ResponseEntity<AddressDto>(HttpStatus.FORBIDDEN);
		}
		address.setAddressStateType(new AddressStateTypeDto(null, AddressStateTypes.HIDDEN.name(),
				null));
		AddressDto hiddenAddress = addressService.updateAddressState(address);
		return new ResponseEntity<AddressDto>(hiddenAddress, HttpStatus.OK);
	}

	@RequestMapping(value = {PROCESS_RELABEL_ADDRESS_MAPPING}, method = RequestMethod.POST, 
			produces = {Constants.JSON_MEDIA_TYPE})
	@ResponseBody
	public ResponseEntity<AddressDto> processRelabelAddress(@ModelAttribute(ADDRESS_ATTRIBUTE) 
			AddressDto address) {
		if (!walletService.checkWalletOwnership(address.getWalletId(), "rebel_sloth")) {
			return new ResponseEntity<AddressDto>(HttpStatus.FORBIDDEN);
		}
		if (!addressService.checkAddressOwnership(address.getAddressId(), "rebel_sloth")) {
			return new ResponseEntity<AddressDto>(HttpStatus.FORBIDDEN);
		}
		AddressDto relabeledAddress = addressService.updateAddress(address);
		return new ResponseEntity<AddressDto>(relabeledAddress, HttpStatus.OK);
	}
}