package com.neemre.bitplexus.frontend.controller.misc;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestMappings {

	public static final String CUSTOMER_MAPPING = "customer";
	public static final String MEMBER_MAPPING = "member";
	public static final String ROOT_MAPPING = "";

	public static final String PROCESS_ARCHIVE_WALLET_MAPPING = "wallets/{walletId}/archive";
	public static final String PROCESS_DELETE_ADDRESS_MAPPING = "wallets/{walletId}/subwallets/"
			+ "{subwalletId}/addresses/{addressId}/delete";
	public static final String PROCESS_HIDE_ADDRESS_MAPPING = "wallets/{walletId}/subwallets/"
			+ "{subwalletId}/addresses/{addressId}/hide";
	public static final String PROCESS_NEW_ADDRESS_MAPPING = "wallets/{walletId}/subwallets/"
			+ "{subwalletId}/addresses";
	public static final String PROCESS_NEW_TRANSACTION_MAPPING = "wallets/{walletId}/subwallets/"
			+ "{subwalletId}/transactions";
	public static final String PROCESS_NEW_WALLET_MAPPING = "wallets";
	public static final String PROCESS_RELABEL_ADDRESS_MAPPING = "wallets/{walletId}/subwallets/"
			+ "{subwalletId}/addresses/{addressId}/relabel";
	public static final String PROCESS_RENAME_WALLET_MAPPING = "wallets/{walletId}/rename";
	public static final String VIEW_ALL_ADDRESSES_MAPPING_1 = "wallets/{walletId}/subwallets/"
			+ "{subwalletId}/addresses";
	public static final String VIEW_ALL_ADDRESSES_MAPPING_2 = "wallets/{walletId}/subwallets/"
			+ "{subwalletId}/transactions/receive";
	public static final String VIEW_ALL_EVENTS_MAPPING_1 = "activity";
	public static final String VIEW_ALL_EVENTS_MAPPING_2 = "events";
	public static final String VIEW_ALL_SUBWALLETS_MAPPING = "wallets/{walletId}/subwallets";
	public static final String VIEW_ALL_TRANSACTIONS_MAPPING = "wallets/{walletId}/subwallets/"
			+ "{subwalletId}/transactions";
	public static final String VIEW_ALL_WALLETS_MAPPING = "wallets";
	public static final String VIEW_NEW_TRANSACTION_MAPPING_1 = "wallets/{walletId}/subwallets/"
			+ "{subwalletId}/transactions/new";
	public static final String VIEW_NEW_TRANSACTION_MAPPING_2 = "wallets/{walletId}/subwallets/"
			+ "{subwalletId}/transactions/send";

	public static final String REDIRECT_ERROR_403_MAPPING = "redirect/403";
	public static final String REDIRECT_ERROR_404_MAPPING = "redirect/404";
	public static final String REDIRECT_ERROR_500_MAPPING = "redirect/500";
	public static final String VIEW_ERROR_403_MAPPING = "403";
	public static final String VIEW_ERROR_404_MAPPING = "404";
	public static final String VIEW_ERROR_500_MAPPING = "500";
	public static final String VIEW_HOME_MAPPING = "home";
	public static final String VIEW_LOGIN_MAPPING = "login";
}