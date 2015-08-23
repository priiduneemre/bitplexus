package com.neemre.bitplexus.backend.model.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum TransactionEndpointTypes {
	
	INPUT,
	OUTPUT_MAIN,
	OUTPUT_CHANGE;
}