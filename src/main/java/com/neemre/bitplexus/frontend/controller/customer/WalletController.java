package com.neemre.bitplexus.frontend.controller.customer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.neemre.bitplexus.backend.model.enums.WalletStateTypes;
import com.neemre.bitplexus.backend.service.ChainService;
import com.neemre.bitplexus.backend.service.CurrencyService;
import com.neemre.bitplexus.backend.service.WalletService;
import com.neemre.bitplexus.common.Constants;
import com.neemre.bitplexus.common.dto.ChainDto;
import com.neemre.bitplexus.common.dto.CurrencyDto;
import com.neemre.bitplexus.common.dto.WalletDto;
import com.neemre.bitplexus.common.dto.WalletStateTypeDto;
import com.neemre.bitplexus.common.util.ServletUtils;
import com.neemre.bitplexus.frontend.controller.misc.Views;

import static com.neemre.bitplexus.frontend.controller.misc.RequestAttributes.*;
import static com.neemre.bitplexus.frontend.controller.misc.RequestMappings.*;

@Controller
@RequestMapping(CUSTOMER_MAPPING)
public class WalletController {

	@Autowired
	private ChainService chainService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private WalletService walletService;


	@RequestMapping(value = {VIEW_ALL_WALLETS_MAPPING}, method = RequestMethod.GET)
	public String viewAllWallets(ModelMap model) {
		List<WalletDto> wallets = walletService.findWalletsByCustomerUsername("rebel_sloth");
		for (WalletDto wallet : wallets) {
			wallet.setBalance(walletService.findWalletBalance(wallet.getWalletId()));
		}
		model.addAttribute(WALLETS_ATTRIBUTE, wallets);
		model.addAttribute(WALLET_ATTRIBUTE, new WalletDto());
		return Views.ALL_WALLETS_VIEW.getPath();
	}

	@RequestMapping(value = {PROCESS_NEW_WALLET_MAPPING}, method = RequestMethod.POST, 
			produces = {Constants.JSON_MEDIA_TYPE})
	@ResponseBody
	public ResponseEntity<WalletDto> processNewWallet(@ModelAttribute(WALLET_ATTRIBUTE) 
			WalletDto wallet) {
		wallet.setUsername("rebel_sloth");
		WalletDto createdWallet = walletService.createNewWallet(wallet);
		return new ResponseEntity<WalletDto>(createdWallet, HttpStatus.OK);
	}

	@RequestMapping(value = {PROCESS_ARCHIVE_WALLET_MAPPING}, method = RequestMethod.POST, 
			produces = {Constants.JSON_MEDIA_TYPE})
	@ResponseBody
	public ResponseEntity<WalletDto> processArchiveWallet(@ModelAttribute(WALLET_ATTRIBUTE) 
			WalletDto wallet) {
		if (!walletService.checkWalletOwnership(wallet.getWalletId(), "rebel_sloth")) {
			return new ResponseEntity<WalletDto>(HttpStatus.FORBIDDEN);
		}
		wallet.setWalletStateType(new WalletStateTypeDto(null, WalletStateTypes.ARCHIVED.name(), 
				null));
		WalletDto archivedWallet = walletService.updateWalletState(wallet);
		return new ResponseEntity<WalletDto>(archivedWallet, HttpStatus.OK);
	}

	@RequestMapping(value = {PROCESS_RENAME_WALLET_MAPPING}, method = RequestMethod.POST,
			produces = {Constants.JSON_MEDIA_TYPE})
	@ResponseBody
	public ResponseEntity<WalletDto> processRenameWallet(@ModelAttribute(WALLET_ATTRIBUTE) 
			WalletDto wallet) {
		if (!walletService.checkWalletOwnership(wallet.getWalletId(), "rebel_sloth")) {
			return new ResponseEntity<WalletDto>(HttpStatus.FORBIDDEN);
		}
		WalletDto renamedWallet = walletService.updateWallet(wallet);
		return new ResponseEntity<WalletDto>(renamedWallet, HttpStatus.OK);
	}

	@RequestMapping(value = {VIEW_ALL_SUBWALLETS_MAPPING}, method = RequestMethod.GET)
	public String viewAllSubwallets(HttpServletRequest request, 
			@PathVariable(value = WALLET_ID_ATTRIBUTE) Integer walletId, ModelMap model) {
		if (!walletService.checkWalletOwnership(walletId, "rebel_sloth")) {
			return ServletUtils.getRedirectUrl(request, VIEW_ERROR_403_MAPPING);
		}
		Map<ChainDto, BigDecimal> subwallets = new HashMap<ChainDto, BigDecimal>();
		for (ChainDto chain : chainService.findChainsByOperationality(true)) {
			subwallets.put(chain, walletService.findSubwalletBalance(walletId, chain.getCode()));
		}
		model.addAttribute(SUBWALLETS_ATTRIBUTE, subwallets);
		List<CurrencyDto> currencies = currencyService.findAllCurrencies();
		model.addAttribute(CURRENCIES_ATTRIBUTE, currencies);
		return Views.ALL_SUBWALLETS_VIEW.getPath();
	}
}