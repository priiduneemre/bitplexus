package com.neemre.bitplexus.backend.crypto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.neemre.bitplexus.common.Errors;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BitcoinWrapperException extends NodeWrapperException {

	private static final long serialVersionUID = 1L;

	
	public BitcoinWrapperException(Errors error) {
		super(error);
	}
	
	public BitcoinWrapperException(Errors error, String additionalMsg) {
		super(error, additionalMsg);
	}
	
	public BitcoinWrapperException(Errors error, Exception cause) {
		super(error, cause);
	}
}