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
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.backend.service.VisitService;
import com.neemre.bitplexus.backend.service.WalletService;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.AddressStateTypeDto;
import com.neemre.bitplexus.common.dto.TransactionDto;
import com.neemre.bitplexus.common.dto.VisitDto;
import com.neemre.bitplexus.common.dto.WalletDto;
import com.neemre.bitplexus.common.dto.WalletStateTypeDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;
import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.ltcdcli4j.core.LitecoindException;

@Controller
@RequestMapping("")
public class VisitController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private TransactionService transactionService;
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
		VisitDto visit = visitService.findVisitById(76L);
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
		WalletDto wallet = walletService.findWalletById(17);
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
		return walletService.updateWallet(new WalletDto(17, null, null, "New wallet #we34rjg", null,
				null));
	}
	
	@RequestMapping(value = "/customer/wallets/state/update", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowUpdate1(ModelMap model) {
		return walletService.updateWalletState(new WalletDto(17, null, new WalletStateTypeDto(null,
				WalletStateTypes.DELETED.name(), null), null, null, null));
	}
	
	@RequestMapping(value = "/customer/addresses/count", method = RequestMethod.GET)
	@ResponseBody
	public Integer viewShowCount(ModelMap model) {
		return addressService.countSubwalletAddressesByLabel("edium-term", 5, 
				"LITECOIN_TEST3");
	}
	
	@RequestMapping(value = "/customer/addresses/new/external", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowCreateNew2(ModelMap model) {
		return addressService.createNewExternalAddress(new AddressDto(null, null, null, null, 
				null, "m91EALsE1LEaUK8tHzNrBoNn2mWojFHFR", null, null, null), "BITCOIN_TEST3");
	}
	
	@RequestMapping(value = "/customer/addresses/new/wallet", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowCreateNew3(ModelMap model) throws NodeWrapperException {
		return addressService.createNewWalletAddress(new AddressDto(null, 16, null, null, 
				"Anoter address #3345", null, null, null, null), "BITCOIN_TEST3");
	}
	
	@RequestMapping(value = "/customer/address", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowOne2(ModelMap model) {
		AddressDto address = addressService.findAddressById(21L);
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
		return addressService.updateAddress(new AddressDto(21L, null, null, null, 
				"Long-term deposit (0.5 tBTC) address #5", null, null, null, null));
	}

	@RequestMapping(value = "/customer/addresses/balance/update", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowUpdate3(ModelMap model) throws NodeWrapperException {
		return addressService.updateAddressBalance(96L);
	}
	
	@RequestMapping(value = "/customer/addresses/state/update", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowUpdate4(ModelMap model) {
		return addressService.updateAddressState(new AddressDto(21L, null, null, 
				new AddressStateTypeDto(null, AddressStateTypes.DELETED.name(), null), null, null, 
				null, null, null));
	}
	
	@RequestMapping(value = "/customer/transactions", method = RequestMethod.GET)
	@ResponseBody
	public List<TransactionDto> viewShowAll3(ModelMap model) {
		List<TransactionDto> transactions = transactionService.findSubwalletTransactions(4, 
				"BITCOIN_TEST3");
		return transactions;
	}
	
	@RequestMapping(value = "/customer/transaction", method = RequestMethod.GET)
	@ResponseBody
	public TransactionDto viewShowOne3(ModelMap model) {
		TransactionDto transaction = transactionService.findTransactionById(16L);
		return transaction;
	}

	@RequestMapping(value = "/btc/info", method = RequestMethod.GET)
	@ResponseBody
	public com.neemre.btcdcli4j.core.domain.Info getInfo(ModelMap model) throws BitcoindException, 
			com.neemre.btcdcli4j.core.CommunicationException {
		return clientResolver.getBtcdClient("BITCOIN_TEST3").getInfo();
	}
	
	@RequestMapping(value = "/ltc/info", method = RequestMethod.GET)
	@ResponseBody
	public com.neemre.ltcdcli4j.core.domain.Info getInfo1(ModelMap model) throws LitecoindException, 
			com.neemre.ltcdcli4j.core.CommunicationException {
		return clientResolver.getLtcdClient("LITECOIN_TEST3").getInfo();
	}
}