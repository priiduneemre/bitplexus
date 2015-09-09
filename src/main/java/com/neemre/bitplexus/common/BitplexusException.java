package com.neemre.bitplexus.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public abstract class BitplexusException extends Exception {

	private static final long serialVersionUID = 1L;

	private int code;


	public BitplexusException(Errors error) {
		this(error, Constants.STRING_EMPTY);
	}

	public BitplexusException(Errors error, String additionalMsg) {
		super(error.getDescription() + additionalMsg);
		code = error.getCode();
	}

	public BitplexusException(Errors error, Exception cause) {
		this(error, Constants.STRING_EMPTY, cause);
	}

	public BitplexusException(Errors error, String additionalMsg, Exception cause) {
		super(error.getDescription() + additionalMsg, cause);
		code = error.getCode();
	}
}