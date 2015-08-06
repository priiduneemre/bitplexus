package com.neemre.bitplexus.backend.model.reference.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum WalletStateTypes {
	
	CREATED,
	ACTIVE,
	ARCHIVED,
	DELETED;
}