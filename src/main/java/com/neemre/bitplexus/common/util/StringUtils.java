package com.neemre.bitplexus.common.util;

import javax.xml.bind.DatatypeConverter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

	public static byte[] toByteArray(String hex) {
		return DatatypeConverter.parseHexBinary(hex);
	}

	public static String toHexString(byte[] bytes) {
		return DatatypeConverter.printHexBinary(bytes);
	}
}