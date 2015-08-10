package com.neemre.bitplexus.frontend.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.crypto.NodeWrapperResolver;
import com.neemre.bitplexus.backend.model.reference.enums.AddressStateTypes;
import com.neemre.bitplexus.backend.model.reference.enums.WalletStateTypes;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.backend.service.VisitService;
import com.neemre.bitplexus.backend.service.WalletService;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.AddressStateTypeDto;
import com.neemre.bitplexus.common.dto.VisitDto;
import com.neemre.bitplexus.common.dto.WalletDto;
import com.neemre.bitplexus.common.dto.WalletStateTypeDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;
import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.domain.Info;

@Controller
@RequestMapping("")
public class VisitController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private VisitService visitService;
	@Autowired
	private WalletService walletService;
	
	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	@Autowired
	private NodeWrapperResolver clientResolver;
	
	
	@RequestMapping(value = "/member/visits/new", method = RequestMethod.GET)
	@ResponseBody
	public VisitDto viewShowCreateNew(ModelMap model, HttpServletRequest request) {
		return visitService.createNewVisit(new VisitDto(null, "rebel_sloth", request.getRemoteAddr(),
				null));
	}
	
	@RequestMapping(value = "/member/visit", method = RequestMethod.GET)
	@ResponseBody
	public VisitDto viewShowOne(ModelMap model) {
		VisitDto visit = visitService.findVisitById(3L);
		return visit;
	}
	
	@RequestMapping(value = "/member/visits", method = RequestMethod.GET)
	@ResponseBody
	public List<VisitDto> viewShowAll(ModelMap model) {
		List<VisitDto> visits = visitService.findVisitsByMemberUsername("rebel_sloth");
		return visits;
	}
	
	@RequestMapping(value = "/customer/wallets/new", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowCreateNew1(ModelMap model) {
		return walletService.createNewWallet(new WalletDto(null, "rebel_sloth", null, 
				"My new wallet", null, null));
	}
	
	@RequestMapping(value = "/customer/wallet", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowOne1(ModelMap model) {
		WalletDto wallet = walletService.findWalletById(10);
		return wallet;
	}
	
	@RequestMapping(value = "/customer/wallets", method = RequestMethod.GET)
	@ResponseBody
	public List<WalletDto> viewShowAll1(ModelMap model) {
		List<WalletDto> wallets = walletService.findWalletsByCustomerUsername("rebel_sloth");
		return wallets;
	}
	
	@RequestMapping(value = "/customer/wallets/update", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowUpdate(ModelMap model) {
		return walletService.updateWallet(new WalletDto(10, null, null, "New wallet #9432757", null,
				null));
	}
	
	@RequestMapping(value = "/customer/wallets/state/update", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowUpdate1(ModelMap model) {
		return walletService.updateWalletState(new WalletDto(10, null, new WalletStateTypeDto(null,
				WalletStateTypes.ARCHIVED.name(), null), null, null, null));
	}
	
	@RequestMapping(value = "/customer/addresses/count", method = RequestMethod.GET)
	@ResponseBody
	public Integer viewShowCount(ModelMap model) {
		return addressService.countSubwalletAddressesByLabel("Dough for illegal stuff", 10, 
				"BITCOIN_TEST3");
	}
	
	@RequestMapping(value = "/customer/addresses/new", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowCreateNew2(ModelMap model) throws NodeWrapperException {
		return addressService.createNewAddress(new AddressDto(null, 10, null, null, "New address #XYZ",
				null, null, null, null), "BITCOIN_TEST3");
	}
	
	@RequestMapping(value = "/customer/address", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowOne2(ModelMap model) {
		AddressDto address = addressService.findAddressById(15L);
		return address;
	}
	
	@RequestMapping(value = "/customer/addresses", method = RequestMethod.GET)
	@ResponseBody
	public List<AddressDto> viewShowAll2(ModelMap model) {
		List<AddressDto> addresses = addressService.findSubwalletAddresses(5, "BITCOIN_TEST3");
		return addresses;
	}
		
	@RequestMapping(value = "/customer/addresses/update", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowUpdate2(ModelMap model) {
		return addressService.updateAddress(new AddressDto(115L, null, null, null, "New address #XYZ2",
				null, null, null, null));
	}

	@RequestMapping(value = "/customer/addresses/balance/update", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowUpdate3(ModelMap model) throws NodeWrapperException {
		return addressService.updateAddressBalance(115L);
	}
	
	@RequestMapping(value = "/customer/addresses/state/update", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowUpdate4(ModelMap model) {
		return addressService.updateAddressState(new AddressDto(115L, null, null, 
				new AddressStateTypeDto(null, AddressStateTypes.HIDDEN.name(), null), null, null, 
				null, null, null));
	}
	
	@RequestMapping(value = "/btc/info", method = RequestMethod.GET)
	@ResponseBody
	public Info getInfo(ModelMap model) throws BitcoindException, CommunicationException {
		return clientResolver.getBtcdClient("BITCOIN_TEST3").getInfo();
	}
	
	@RequestMapping(value = "/ltc/info", method = RequestMethod.GET)
	@ResponseBody
	public Info getInfo1(ModelMap model) throws BitcoindException, CommunicationException {
		return clientResolver.getBtcdClient("LITECOIN_TEST3").getInfo();
	}
}