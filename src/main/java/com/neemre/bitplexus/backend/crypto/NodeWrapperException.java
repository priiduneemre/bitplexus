package com.neemre.bitplexus.backend.crypto;

import com.neemre.bitplexus.common.BitplexusException;
import com.neemre.bitplexus.common.Errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public abstract class NodeWrapperException extends BitplexusException {

	private static final long serialVersionUID = 1L;


	public NodeWrapperException(Errors error) {
		super(error);
	}

	public NodeWrapperException(Errors error, String additionalMsg) {
		super(error, additionalMsg);
	}

	public NodeWrapperException(Errors error, Exception cause) {
		super(error, cause);
	}

	public NodeWrapperException(Errors error, String additionalMsg, Exception cause) {
		super(error, additionalMsg, cause);
	}
}