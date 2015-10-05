package com.neemre.bitplexus.common.util;

import javax.servlet.http.HttpServletRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.neemre.bitplexus.common.Errors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServletUtils {

	public static String extractBaseUrl(HttpServletRequest request) {
		if (request == null) {
			throw new IllegalArgumentException(Errors.TODO.getDescription());
		}
		return request.getRequestURL().toString().replace(request.getRequestURI(), 
				request.getContextPath());
	}

	public static String getRedirectUrl(HttpServletRequest request, String targetUri) {
		if (targetUri == null) {
			throw new IllegalArgumentException(Errors.TODO.getDescription());
		}
		return String.format("%s:%s/%s", "redirect", extractBaseUrl(request), targetUri);
	}
}