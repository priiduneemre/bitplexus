package com.neemre.bitplexus.backend.service.misc;

import com.neemre.bitplexus.common.BitplexusException;
import com.neemre.bitplexus.common.Errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public abstract class ExternalServiceException extends BitplexusException {

	private static final long serialVersionUID = 1L;


	public ExternalServiceException(Errors error) {
		super(error);
	}

	public ExternalServiceException(Errors error, String additionalMsg) {
		super(error, additionalMsg);
	}

	public ExternalServiceException(Errors error, Exception cause) {
		super(error, cause);
	}

	public ExternalServiceException(Errors error, String additionalMsg, Exception cause) {
		super(error, additionalMsg, cause);
	}
}