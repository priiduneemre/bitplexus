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

import com.neemre.bitplexus.backend.model.reference.enums.WalletStateTypes;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.backend.service.VisitService;
import com.neemre.bitplexus.backend.service.WalletService;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.VisitDto;
import com.neemre.bitplexus.common.dto.WalletDto;
import com.neemre.bitplexus.common.dto.WalletStateTypeDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;
import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;

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
	@Resource(name = "btcdTestnet3Client")
	private BtcdClient btcdClient;
	
	@RequestMapping(value = "/member/visits", method = RequestMethod.GET)
	@ResponseBody
	public List<VisitDto> viewShowAll(ModelMap model) {
		List<VisitDto> visits = visitService.findVisitsByMemberUsername("rebel_sloth");
		return visits;
	}
	
	@RequestMapping(value = "/member/visits/new", method = RequestMethod.GET)
	@ResponseBody
	public VisitDto viewShowCreateNew(ModelMap model, HttpServletRequest request) {
		return visitService.createNewVisit(new VisitDto(null, "rebel_sloth", request.getRemoteAddr(),
				null));
	}
	
	@RequestMapping(value = "/customer/wallets", method = RequestMethod.GET)
	@ResponseBody
	public List<WalletDto> viewShowAll1(ModelMap model) {
		List<WalletDto> wallets = walletService.findWalletsByCustomerUsername("rebel_sloth");
		return wallets;
	}
	
	@RequestMapping(value = "/customer/wallets/new", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowCreateNew1(ModelMap model) {
		return walletService.createNewWallet(new WalletDto(null, "rebel_sloth", null, 
				"My new wallet", null, null));
	}
	
	@RequestMapping(value = "/customer/wallets/update", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowUpdate(ModelMap model) {
		return walletService.updateWallet(new WalletDto(10, null, null, "New wallet #9432757", null,
				null));
	}
	
	@RequestMapping(value = "/customer/wallets/restate", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowUpdate1(ModelMap model) {
		return walletService.updateWalletState(new WalletDto(10, null, new WalletStateTypeDto(null,
				WalletStateTypes.ARCHIVED.name(), null), null, null, null));
	}
	
	@RequestMapping(value = "/customer/addresses", method = RequestMethod.GET)
	@ResponseBody
	public List<AddressDto> viewShowAll2(ModelMap model) {
		List<AddressDto> addresses = addressService.findSubwalletAddresses(5, "BITCOIN_TEST3");
		return addresses;
	}
	
	@RequestMapping(value = "/btc/info", method = RequestMethod.GET)
	@ResponseBody
	public String getInfo(ModelMap model) throws BitcoindException, CommunicationException {
		return btcdClient.getInfo().toString();
	}
}