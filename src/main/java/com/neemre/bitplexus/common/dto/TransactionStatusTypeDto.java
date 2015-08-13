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
@Dto("com.neemre.bitplexus.backend.model.reference.TransactionStatusType")
public class TransactionStatusTypeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "transactionStatusTypeId", readOnly = true)
	private Short transactionStatusTypeId;
	@DtoField(value = "code", readOnly = true)
	private String code;
	@DtoField(value = "name", readOnly = true)
	private String name;
}