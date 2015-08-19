package com.neemre.bitplexus.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum PropertyKeys {

	BITCOIND_WALLET_PASSPHRASE("node.bitcoind.wallet.passphrase"),
	LITECOIND_WALLET_PASSPHRASE("node.litecoind.wallet.passphrase"),
	TICKER_URLBASE("api.rest.ticker.urlbase"),
	TICKER_ENDPOINT_BITCOIN("api.rest.ticker.endpoint.bitcoin"),
	TICKER_ENDPOINT_LITECOIN("api.rest.ticker.endpoint.litecoin");

	private final String value;
}