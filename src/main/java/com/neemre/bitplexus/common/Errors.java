package com.neemre.bitplexus.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Errors {
	
	ARGS_CLASS_UNINSANTIATABLE(1, ""),
	ARGS_CLASS_INVISIBLE(2, ""),
	TODO(1, "TODO");
	
	private final int code;
	private final String message;
	
	
	public String getDescription() {
		return String.format("Error #%s: %s", code, message);
	}
}