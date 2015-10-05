package com.neemre.bitplexus.backend.model.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum AddressStateTypes {

	ALLOCATED,
	ACTIVE,
	USED,
	HIDDEN,
	DELETED,
	NOT_APPLICABLE;


	public String getName() {
		return name();
	}
}