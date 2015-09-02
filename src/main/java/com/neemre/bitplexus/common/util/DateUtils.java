package com.neemre.bitplexus.common.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.neemre.bitplexus.common.Errors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

	public static Date toDate(long unixTimestamp) {
		if(unixTimestamp < 0) {
			throw new IllegalArgumentException(Errors.TODO.getDescription());
		} 
		return new Date(TimeUnit.SECONDS.toMillis(unixTimestamp));
	}

	public static Date toDate(String unixTimestamp) {
		return toDate(Long.valueOf(unixTimestamp));
	}
}