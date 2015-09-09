package com.neemre.bitplexus.backend.service.misc;

import com.neemre.bitplexus.common.Errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CryptonatorException extends ExternalServiceException {

	private static final long serialVersionUID = 1L;


	public CryptonatorException(Errors error) {
		super(error);
	}

	public CryptonatorException(Errors error, String additionalMsg) {
		super(error, additionalMsg);
	}

	public CryptonatorException(Errors error, Exception cause) {
		super(error, cause);
	}

	public CryptonatorException(Errors error, String additionalMsg, Exception cause) {
		super(error, additionalMsg, cause);
	}
}