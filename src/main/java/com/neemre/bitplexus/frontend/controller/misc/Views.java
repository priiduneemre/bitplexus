package com.neemre.bitplexus.frontend.controller.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Views {

	ALL_ADDRESSES_VIEW("customer/addresses/address_list"),
	ALL_EVENTS_VIEW("member/activity/activity_list"),
	ALL_SUBWALLETS_VIEW("customer/wallets/subwallet_list"),
	ALL_TRANSACTIONS_VIEW("customer/transactions/transaction_list"),
	ALL_WALLETS_VIEW("customer/wallets/wallet_list"),
	ERROR_403_VIEW("error/client/403"),
	ERROR_404_VIEW("error/client/404"),
	ERROR_500_VIEW("error/server/500"),
	HOME_VIEW("index"),
	LOGIN_VIEW("login"),
	NEW_ADDRESS_VIEW("customer/addresses/address_new"),
	NEW_TRANSACTION_VIEW("customer/transactions/transaction_new"),
	NEW_WALLET_VIEW("customer/wallets/wallet_new"),
	RELABEL_ADDRESS_VIEW("customer/addresses/address_relabel"),
	RENAME_WALLET_VIEW("customer/wallets/wallet_rename");

	private final String path;
}