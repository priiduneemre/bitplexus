package com.neemre.bitplexus.backend.crypto;

import com.neemre.bitplexus.common.Constants;
import com.neemre.bitplexus.common.Errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public abstract class NodeWrapperException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private int code;
	
	
	public NodeWrapperException(Errors error) {
		this(error, Constants.STRING_EMPTY);
	}
	
	public NodeWrapperException(Errors error, String additionalMsg) {
		super(error.getDescription() + additionalMsg);
		code = error.getCode();
	}
	
	public NodeWrapperException(Errors error, Exception cause) {
		super(error.getDescription(), cause);
		code = error.getCode();
	}
}