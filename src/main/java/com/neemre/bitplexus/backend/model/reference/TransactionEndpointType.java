package com.neemre.bitplexus.backend.model.reference;

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
public class TransactionEndpointType extends ReferenceEntity {

	private Short transactionEndpointTypeId;
	private String code;
	private String name;
}