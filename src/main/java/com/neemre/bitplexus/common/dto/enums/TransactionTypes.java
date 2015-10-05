package com.neemre.bitplexus.common.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum TransactionTypes {

	INCOMING("Incoming transaction"),
	INTERNAL("Internal transfer"),
	OUTGOING("Outgoing transaction");

	private final String name;
}