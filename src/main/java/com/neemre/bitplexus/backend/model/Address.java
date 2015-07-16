package com.neemre.bitplexus.backend.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.neemre.bitplexus.backend.model.reference.AddressStateType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Address extends Entity {
	
	private Long addressId;
	private Wallet wallet;
	private AddressType addressType;
	private AddressStateType addressStateType;
	private String label;
	private String encodedForm;
	private BigDecimal balance;
	private Date indexedAt;
	private Date updatedAt;
}