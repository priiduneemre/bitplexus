package com.neemre.bitplexus.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Defaults {

	public static final int CONFIRMED_CONF_COUNT = 1;
	public static final int COMPLETED_CONF_COUNT = 3;
	
	public static final int PASSPHRASE_TIMEOUT = 5;
}