package com.neemre.bitplexus.backend.model.reference.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum TransactionStatusTypes {
	
	ASSEMBLED,
	UNCONFIRMED,
	CONFIRMED,
	COMPLETED,
	FAILED,
	DROPPED;
}