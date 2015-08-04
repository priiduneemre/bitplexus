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
@Dto("com.neemre.bitplexus.backend.model.Customer")
public class CustomerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "customerId", readOnly = true)
	private Integer customerId;
}