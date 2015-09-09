package com.neemre.bitplexus.backend.crypto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.neemre.bitplexus.common.Errors;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class LitecoinWrapperException extends NodeWrapperException {

	private static final long serialVersionUID = 1L;


	public LitecoinWrapperException(Errors error) {
		super(error);
	}

	public LitecoinWrapperException(Errors error, String additionalMsg) {
		super(error, additionalMsg);
	}

	public LitecoinWrapperException(Errors error, Exception cause) {
		super(error, cause);
	}

	public LitecoinWrapperException(Errors error, String additionalMsg, Exception cause) {
		super(error, additionalMsg, cause);
	}
}