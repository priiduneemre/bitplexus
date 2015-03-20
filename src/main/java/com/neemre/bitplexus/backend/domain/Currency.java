package com.neemre.bitplexus.backend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Currency extends Entity {

	private Integer currencyId;
	private String name;
	private String abbreviation;
	private String symbol;
}