package com.neemre.bitplexus.backend.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Currencies {
	
	BITCOIN("Bitcoin"),
	LITECOIN("Litecoin");
	
	private final String name;
}