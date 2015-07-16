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
public class Chain extends Entity {
	
	private Short chainId;
	private Currency currency;
	private String code;
	private String name;
	private Date startedOn;
	private BigDecimal availableSupply;
	private Boolean isOperational;
	private Date createdAt;
	private Employee createdBy;
	private Date updatedAt;
	private Employee updatedBy;
}