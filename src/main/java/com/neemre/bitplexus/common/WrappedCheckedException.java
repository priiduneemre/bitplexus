package com.neemre.bitplexus.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class WrappedCheckedException extends RuntimeException {

	private static final long serialVersionUID = 1L;


	public WrappedCheckedException(Exception cause) {
		super("The operation threw an unhandled checked exception (see 'Throwable#getCause()' for "
				+ "details).", cause);
	}

	public WrappedCheckedException(String message, Exception cause) {
		super(message, cause);
	}
}