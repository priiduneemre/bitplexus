package com.neemre.bitplexus.backend.model.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum WalletStateTypes {

	CREATED,
	ACTIVE,
	ARCHIVED,
	DELETED;


	public String getName() {
		return name();
	}
}