package com.neemre.bitplexus.backend.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.neemre.bitplexus.backend.model.reference.TransactionEndpointType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class TransactionEndpoint extends Entity {
	
	private Long transactionEndpointId;
	private Transaction transaction;
	private Address address;
	private TransactionEndpointType transactionEndpointType;
	private BigDecimal amount;
	private Date loggedAt;
}