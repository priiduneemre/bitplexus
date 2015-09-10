package com.neemre.bitplexus.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Errors {

	TODO(10001, "TODO: define custom error codes & messages!");

	private final int code;
	private final String message;


	public String getDescription() {
		return String.format("Error #%s: %s", code, message);
	}
}