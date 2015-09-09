package com.neemre.bitplexus.frontend.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neemre.bitplexus.backend.crypto.BitcoinWrapperException;
import com.neemre.bitplexus.backend.crypto.LitecoinWrapperException;
import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.crypto.adapter.NodeClientAdapter;
import com.neemre.bitplexus.backend.model.enums.AddressStateTypes;
import com.neemre.bitplexus.backend.model.enums.WalletStateTypes;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.backend.service.ChainService;
import com.neemre.bitplexus.backend.service.MemberService;
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.backend.service.VisitService;
import com.neemre.bitplexus.backend.service.WalletService;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.dto.AddressStateTypeDto;
import com.neemre.bitplexus.common.dto.ChainDto;
import com.neemre.bitplexus.common.dto.MemberDto;
import com.neemre.bitplexus.common.dto.TransactionDto;
import com.neemre.bitplexus.common.dto.VisitDto;
import com.neemre.bitplexus.common.dto.WalletDto;
import com.neemre.bitplexus.common.dto.WalletStateTypeDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;
import com.neemre.bitplexus.common.dto.virtual.PaymentDetailsDto;

@Controller
@RequestMapping("")
public class VisitController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private ChainService chainService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private VisitService visitService;
	@Autowired
	private WalletService walletService;

	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	@Autowired
	private NodeClientAdapter nodeClient;


	@RequestMapping(value = "/customer/addresses/count", method = RequestMethod.GET)
	@ResponseBody
	public Integer viewShowCount(ModelMap model) {
		return addressService.countSubwalletAddressesByLabel("edium-term", 5, 
				"LITECOIN_TEST3");
	}

	@RequestMapping(value = "/customer/addresses/new/external", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowCreateNew(ModelMap model) {
		return addressService.createNewExternalAddress(new AddressDto(null, null, null, null, 
				null, "m91EALsE1LEaUK8tHzNrBoNn2mWojFHFR", null, null, null), "BITCOIN_TEST3");
	}

	@RequestMapping(value = "/customer/addresses/new/wallet", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowCreateNew1(ModelMap model) throws NodeWrapperException {
		return addressService.createNewWalletAddress(new AddressDto(null, 16, null, null, 
				"Anoter address #3345", null, null, null, null), false, "LITECOIN_TEST3");
	}

	@RequestMapping(value = "/customer/address/encodedForm", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowOne(ModelMap model) {
		AddressDto address = addressService.findAddressByEncodedForm(
				"mq4gQZwpzW9fr4vhoPoANz7eMZKwTqx8rT");
		return address;
	}

	@RequestMapping(value = "/customer/address", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowOne1(ModelMap model) {
		AddressDto address = addressService.findAddressById(21L);
		return address;
	}

	@RequestMapping(value = "/customer/addresses/transaction", method = RequestMethod.GET)
	@ResponseBody
	public List<String> viewShowAll(ModelMap model) {
		List<String> encodedForms = addressService.findAddressesByTransactionNetworkUid(
				"45a48c901df784c55102b233ce3878345350068a6e74c00fe2883b2355d397bc");
		return encodedForms;
	}

	@RequestMapping(value = "/customer/addresses/chain/external", method = RequestMethod.GET)
	@ResponseBody
	public List<AddressDto> viewShowAll1(ModelMap model) {
		List<AddressDto> addresses = addressService.findExternalAddressesByChainCode("BITCOIN_TEST3");
		return addresses;
	}

	@RequestMapping(value = "/customer/addresses", method = RequestMethod.GET)
	@ResponseBody
	public List<AddressDto> viewShowAll2(ModelMap model) {
		List<AddressDto> addresses = addressService.findSubwalletAddresses(5, "BITCOIN_TEST3");
		return addresses;
	}

	@RequestMapping(value = "/customer/addresses/chain/wallet", method = RequestMethod.GET)
	@ResponseBody
	public List<AddressDto> viewShowAll3(ModelMap model) {
		List<AddressDto> addresses = addressService.findWalletAddressesByChainCode("BITCOIN_TEST3");
		return addresses;
	}

	@RequestMapping(value = "/customer/addresses/update", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowUpdate(ModelMap model) {
		return addressService.updateAddress(new AddressDto(21L, null, null, null, 
				"Long-term deposit (0.5 tBTC) address #5", null, null, null, null));
	}

	@RequestMapping(value = "/customer/addresses/balance/update", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowUpdate1(ModelMap model) throws NodeWrapperException {
		return addressService.updateAddressBalance(96L);
	}

	@RequestMapping(value = "/customer/addresses/state/update", method = RequestMethod.GET)
	@ResponseBody
	public AddressDto viewShowUpdate2(ModelMap model) {
		return addressService.updateAddressState(new AddressDto(21L, null, null, 
				new AddressStateTypeDto(null, AddressStateTypes.DELETED.name(), null), null, null, 
				null, null, null));
	}

	@RequestMapping(value = "/chains", method = RequestMethod.GET)
	@ResponseBody
	public List<ChainDto> viewShowAll4(ModelMap model) {
		List<ChainDto> chains = chainService.findChainsByOperationality(true);
		return chains;
	}

	@RequestMapping(value = "/chains/price", method = RequestMethod.GET)
	@ResponseBody
	public BigDecimal viewShowExternalApiResult(ModelMap model) {
		return chainService.findChainUnitPrice("BITCOIN_TEST3");
	}

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	@ResponseBody
	public MemberDto viewShowOne2(ModelMap model) {
		MemberDto member = memberService.findMemberByUsername("rebel_sloth");
		return member;
	}

	@RequestMapping(value = "/member/roles", method = RequestMethod.GET)
	@ResponseBody
	public List<String> viewShowAll5(ModelMap model) {
		List<String> memberRoles = memberService.findMemberRolesByUsername("rebel_sloth");
		return memberRoles;
	}

	@RequestMapping(value = "/customer/transactions/complete", method = RequestMethod.GET)
	@ResponseBody
	public List<String> viewShowComplexQueryResult(ModelMap model) {
		List<String> completedNetworkUids = transactionService.completeTransactions(532797, 
				new Date(), "BITCOIN_TEST3");
		return completedNetworkUids;
	}

	@RequestMapping(value = "/customer/transactions/confirm", method = RequestMethod.GET)
	@ResponseBody
	public List<String> viewShowComplexQueryResult1(ModelMap model) {
		List<String> confirmedNetworkUids = transactionService.confirmTransactions(Arrays.asList(
				new String[]{"e2bdb2e0bcc65462008f90ef5e0f32a867bfe3e84c61d36db29c8667ee2a1fad",
						"9a2fb291dd54a8ee06c4aa802da1747ff7b1f955a316cbf0544e138e9fe7fdd6", 
						"26cc7d07b9811f22beffb60cf32497da216a007648c629f04d9b55642ce43394"}),
						532795, new Date(), "BITCOIN_TEST3");
		return confirmedNetworkUids;
	}

	@RequestMapping(value = "/customer/transactions/drop", method = RequestMethod.GET)
	@ResponseBody
	public List<String> viewShowComplexQueryResult2(ModelMap model) {
		List<String> droppedNetworkUids = transactionService.dropTransactions("BITCOIN_TEST3");
		return droppedNetworkUids;
	}

	@RequestMapping(value = "/customer/transactions", method = RequestMethod.GET)
	@ResponseBody
	public List<TransactionDto> viewShowAll6(ModelMap model) {
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

	@RequestMapping(value = "/customer/transaction/networkUid", method = RequestMethod.GET)
	@ResponseBody
	public TransactionDto viewShowOne4(ModelMap model) {
		TransactionDto transaction = transactionService.findTransactionByNetworkUid(
				"45a48c901df784c55102b233cc3878345350068a6e74c00fe2983b2355d397bc");
		return transaction;
	}

	@RequestMapping(value = "/customer/transactions/fee/minimum", method = RequestMethod.GET)
	@ResponseBody
	public BigDecimal viewShowComplexCalcResult(ModelMap model) {
		return transactionService.findTransactionMinimumFee("LITECOIN_TEST3");
	}

	@RequestMapping(value = "/customer/transactions/fee/optimal", method = RequestMethod.GET)
	@ResponseBody
	public  BigDecimal viewShowComplexCalcResult1(ModelMap model) {
		return transactionService.findTransactionOptimalFee(new BigDecimal("12.95"), 16, 
				"LITECOIN_TEST3");
	}

	@RequestMapping(value = "/customer/transactions/new/incoming", method = RequestMethod.GET)
	@ResponseBody
	public TransactionDto viewShowCreateNew2(ModelMap model) throws NodeWrapperException {
		return transactionService.receiveNewTransaction("6dbf3f7587048da3570056d66b16e5fb48e4b74e69"
				+ "6177d8a1a714c87e0bf2dc", "BITCOIN_TEST3");
	}

	@RequestMapping(value = "/customer/transactions/new/outgoing", method = RequestMethod.GET)
	@ResponseBody
	public TransactionDto viewShowCreateNew3(ModelMap model) throws NodeWrapperException {
		return transactionService.sendNewTransaction(new PaymentDetailsDto("2MtWnaT5wuuTBjd2PJY5WfT"
				+ "Bbs1yqmykcHs", new BigDecimal("6.8"), "This is an outgoing test transaction!"), 
				16, "LITECOIN_TEST3");
	}

	@RequestMapping(value = "/member/visits/new", method = RequestMethod.GET)
	@ResponseBody
	public VisitDto viewShowCreateNew4(ModelMap model, HttpServletRequest request) {
		return visitService.createNewVisit(new VisitDto(null, "rebel_sloth", request.getRemoteAddr(),
				null));
	}

	@RequestMapping(value = "/member/visit", method = RequestMethod.GET)
	@ResponseBody
	public VisitDto viewShowOne5(ModelMap model) {
		VisitDto visit = visitService.findVisitById(76L);
		return visit;
	}

	@RequestMapping(value = "/member/visits", method = RequestMethod.GET)
	@ResponseBody
	public List<VisitDto> viewShowAll7(ModelMap model) {
		List<VisitDto> visits = visitService.findVisitsByMemberUsername("rebel_sloth");
		return visits;
	}

	@RequestMapping(value = "/customer/wallets/count", method = RequestMethod.GET)
	@ResponseBody
	public Integer viewShowCount2(ModelMap model) {
		return walletService.countCustomerWalletsByName("My new wallet", 5);
	}

	@RequestMapping(value = "/customer/wallets/new", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowCreateNew5(ModelMap model) {
		return walletService.createNewWallet(new WalletDto(null, "rebel_sloth", null, 
				"My new wallet", null, null));
	}

	@RequestMapping(value = "/customer/wallets/chain/balance", method = RequestMethod.GET)
	@ResponseBody
	public BigDecimal viewShowSum(ModelMap model) {
		return walletService.findSubwalletBalance(1, "BITCOIN_TEST3");
	}

	@RequestMapping(value = "/customer/wallet", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowOne6(ModelMap model) {
		WalletDto wallet = walletService.findWalletById(17);
		return wallet;
	}

	@RequestMapping(value = "/customer/wallets", method = RequestMethod.GET)
	@ResponseBody
	public List<WalletDto> viewShowAll8(ModelMap model) {
		List<WalletDto> wallets = walletService.findWalletsByCustomerUsername("rebel_sloth");
		return wallets;
	}

	@RequestMapping(value = "/customer/wallets/update", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowUpdate3(ModelMap model) {
		return walletService.updateWallet(new WalletDto(17, null, null, "New wallet #we34rjg", null,
				null));
	}

	@RequestMapping(value = "/customer/wallets/state/update", method = RequestMethod.GET)
	@ResponseBody
	public WalletDto viewShowUpdate4(ModelMap model) {
		return walletService.updateWalletState(new WalletDto(17, null, new WalletStateTypeDto(null,
				WalletStateTypes.DELETED.name(), null), null, null, null));
	}

	@RequestMapping(value = "/btc/info", method = RequestMethod.GET)
	@ResponseBody
	public com.neemre.btcdcli4j.core.domain.Info getInfo(ModelMap model) 
			throws BitcoinWrapperException {
		return nodeClient.getBtcInfo("BITCOIN_TEST3");
	}

	@RequestMapping(value = "/ltc/info", method = RequestMethod.GET)
	@ResponseBody
	public com.neemre.ltcdcli4j.core.domain.Info getInfo1(ModelMap model) 
			throws LitecoinWrapperException {
		return nodeClient.getLtcInfo("LITECOIN_TEST3");
	}
}