package com.neemre.bitplexus.frontend.controller.customer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.service.ChainService;
import com.neemre.bitplexus.backend.service.CurrencyService;
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.backend.service.WalletService;
import com.neemre.bitplexus.common.dto.ChainDto;
import com.neemre.bitplexus.common.dto.CurrencyDto;
import com.neemre.bitplexus.common.dto.TransactionDto;
import com.neemre.bitplexus.common.dto.virtual.PaymentDetailsDto;
import com.neemre.bitplexus.common.util.ServletUtils;
import com.neemre.bitplexus.frontend.controller.misc.Views;

import static com.neemre.bitplexus.frontend.controller.misc.RequestAttributes.*;
import static com.neemre.bitplexus.frontend.controller.misc.RequestMappings.*;

@Controller
@RequestMapping(CUSTOMER_MAPPING)
public class TransactionController {

	@Autowired
	private ChainService chainService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private WalletService walletService;


	@RequestMapping(value = {VIEW_ALL_TRANSACTIONS_MAPPING}, method = RequestMethod.GET)
	public String viewAllTransactions(HttpServletRequest request, @PathVariable(WALLET_ID_ATTRIBUTE) 
			Integer walletId, @PathVariable(SUBWALLET_ID_ATTRIBUTE) Short subwalletId, 
			ModelMap model) throws NodeWrapperException {
		if (!chainService.checkChainOperationality(subwalletId)) {
			return ServletUtils.getRedirectUrl(request, VIEW_ERROR_404_MAPPING);
		}
		if (!walletService.checkWalletOwnership(walletId, "rebel_sloth")) {
			return ServletUtils.getRedirectUrl(request, VIEW_ERROR_403_MAPPING);
		}
		String chainCode = chainService.findChainById(subwalletId).getCode();
		List<TransactionDto> transactions = transactionService.findSubwalletTransactions(walletId, 
				chainCode);
		for (TransactionDto transaction : transactions) {
			Long transactionId = transaction.getTransactionId();
			transaction.setTransactionType(transactionService.findTransactionType(transactionId));
			transaction.setConfirmations(transactionService.findTransactionConfirmations(transactionId));
		}
		model.addAttribute(TRANSACTIONS_ATTRIBUTE, transactions);
		CurrencyDto currency = currencyService.findCurrencyByChainCode(chainCode);
		model.addAttribute(CURRENCY_ATTRIBUTE, currency);
		return Views.ALL_TRANSACTIONS_VIEW.getPath();
	}

	@RequestMapping(value = {VIEW_NEW_TRANSACTION_MAPPING_1, VIEW_NEW_TRANSACTION_MAPPING_2}, 
			method = RequestMethod.GET)
	public String viewNewTransaction(HttpServletRequest request, @PathVariable(WALLET_ID_ATTRIBUTE)
			Integer walletId, @PathVariable(SUBWALLET_ID_ATTRIBUTE) Short subwalletId, ModelMap model) {
		if (!chainService.checkChainOperationality(subwalletId)) {
			return ServletUtils.getRedirectUrl(request, VIEW_ERROR_404_MAPPING);
		}
		if (!walletService.checkWalletOwnership(walletId, "rebel_sloth")) {
			return ServletUtils.getRedirectUrl(request, VIEW_ERROR_403_MAPPING);
		}		
		Map<ChainDto, BigDecimal> chains = new HashMap<ChainDto, BigDecimal>();
		for (ChainDto chain : chainService.findChainsByOperationality(true)) {
			chains.put(chain, chainService.findChainUnitPrice(chain.getCode()));
		}
		model.addAttribute(CHAINS_ATTRIBUTE, chains);
		List<CurrencyDto> currencies = currencyService.findAllCurrencies();
		model.addAttribute(CURRENCIES_ATTRIBUTE, currencies);
		String chainCode = chainService.findChainById(subwalletId).getCode();
		BigDecimal chainRate = chainService.findChainUnitPrice(chainCode);
		model.addAttribute(CHAIN_RATE_ATTRIBUTE, chainRate);
		CurrencyDto currency = currencyService.findCurrencyByChainCode(chainCode);
		model.addAttribute(CURRENCY_ATTRIBUTE, currency);
		model.addAttribute(PAYMENT_DETAILS_ATTRIBUTE, new PaymentDetailsDto());
		return Views.NEW_TRANSACTION_VIEW.getPath();
	}

	@RequestMapping(value = {PROCESS_NEW_TRANSACTION_MAPPING}, method = RequestMethod.POST)
	public String processNewTransaction(HttpServletRequest request, @PathVariable(WALLET_ID_ATTRIBUTE)
			Integer walletId, @PathVariable(SUBWALLET_ID_ATTRIBUTE) Short subwalletId, 
			@ModelAttribute(PAYMENT_DETAILS_ATTRIBUTE) PaymentDetailsDto paymentDetails, 
			RedirectAttributes redirectModel) throws NodeWrapperException {
		if (!chainService.checkChainOperationality(subwalletId)) {
			return ServletUtils.getRedirectUrl(request, VIEW_ERROR_404_MAPPING);
		}
		if (!walletService.checkWalletOwnership(walletId, "rebel_sloth")) {
			return ServletUtils.getRedirectUrl(request, VIEW_ERROR_403_MAPPING);
		}
		String chainCode = chainService.findChainById(subwalletId).getCode();
		if (!walletService.checkSubwalletSufficientFunds(paymentDetails.getAmount(), walletId, 
				chainCode)) {
			redirectModel.addFlashAttribute(DANGER_ATTRIBUTE, true);
			redirectModel.addFlashAttribute(DANGER_HEADING_ATTRIBUTE, "Whoops!");
			redirectModel.addFlashAttribute(DANGER_BODY_ATTRIBUTE, "Insufficient funds to complete "
					+ "this transaction.");
		} else {
			transactionService.sendNewTransaction(paymentDetails, walletId, chainCode);
			redirectModel.addFlashAttribute(SUCCESS_ATTRIBUTE, true);
			redirectModel.addFlashAttribute(SUCCESS_HEADING_ATTRIBUTE, "Done!");
			redirectModel.addFlashAttribute(SUCCESS_BODY_ATTRIBUTE, "Transaction completed "
					+ "successfully.");
		}
		return ServletUtils.getRedirectUrl(request, String.format("%s/%s", CUSTOMER_MAPPING,
				VIEW_NEW_TRANSACTION_MAPPING_1.replace("{walletId}", String.valueOf(walletId))
				.replace("{subwalletId}", String.valueOf(subwalletId))));
	}
}