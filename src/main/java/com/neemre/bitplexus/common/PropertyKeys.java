package com.neemre.bitplexus.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum PropertyKeys {

	ADDRESS_CHANGE_LABEL_DEFAULT("service.address.change.label.default"),
	ADDRESS_REGULAR_LABEL_DEFAULT("service.address.regular.label.default"),
	BITCOIND_WALLET_PASSPHRASE("node.bitcoind.wallet.passphrase"),
	LITECOIND_WALLET_PASSPHRASE("node.litecoind.wallet.passphrase"),
	TICKER_ENDPOINT_BITCOIN("api.rest.ticker.endpoint.bitcoin"),
	TICKER_ENDPOINT_LITECOIN("api.rest.ticker.endpoint.litecoin"),
	TICKER_URLBASE("api.rest.ticker.urlbase"),
	WALLET_NAME_DEFAULT("service.wallet.name.default");

	private final String value;
}