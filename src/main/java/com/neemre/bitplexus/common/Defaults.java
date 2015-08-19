package com.neemre.bitplexus.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Defaults {

	public static final int DECIMAL_SCALE = 8;
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

	public static final int UNCONFIRMED_CONF_COUNT = 0;
	public static final int CONFIRMED_CONF_COUNT = 1;
	public static final int BTC_COMPLETED_CONF_COUNT = 3;
	public static final int LTC_COMPLETED_CONF_COUNT = 12;

	public static final BigDecimal TX_FEE_COEFFICIENT = new BigDecimal("3.0");

	public static final boolean ALLOW_HIGH_FEES = false;
	public static final int PASSPHRASE_TIMEOUT = 1;
	public static final int RAW_TX_VERBOSITY = 1;
}