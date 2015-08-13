package com.neemre.bitplexus.common.dto;

import java.io.Serializable;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Dto("com.neemre.bitplexus.backend.model.reference.TransactionEndpointType")
public class TransactionEndpointTypeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@DtoField(value = "transactionEndpointTypeId", readOnly = true)
	private Short transactionEndpointTypeId;
	@DtoField(value = "code", readOnly = true)
	private String code;
	@DtoField(value = "name", readOnly = true)
	private String name;
}