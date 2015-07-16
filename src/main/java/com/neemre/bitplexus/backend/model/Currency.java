package com.neemre.bitplexus.backend.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Currency extends Entity {

	private Short currencyId;
	private String name;
	private String abbreviation;
	private String symbol;
	private Date launchedOn;
	private Integer blockTime;
	private BigDecimal standardFee;
	private String websiteUrl;
	private Date createdAt;
	private Date updatedAt;
	private Employee updatedBy;
}