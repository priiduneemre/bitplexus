package com.neemre.bitplexus.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Defaults {

	public static final int UNCONFIRMED_CONF_COUNT = 0;
	public static final int CONFIRMED_CONF_COUNT = 1;
	public static final int BTC_COMPLETED_CONF_COUNT = 3;
	public static final int LTC_COMPLETED_CONF_COUNT = 12;
	
	public static final int PASSPHRASE_TIMEOUT = 5;
}