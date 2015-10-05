package com.neemre.bitplexus.common.dto;

import java.io.Serializable;
import java.util.Date;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Dto("com.neemre.bitplexus.backend.model.AddressType")
public class AddressTypeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "addressTypeId", readOnly = true)
	private Short addressTypeId;
	@DtoField(value = "chain.chainId", readOnly = true, entityBeanKeys = {"Chain"})
	private Short chainId;
	@DtoField("code")
	private String code;
	@DtoField("name")
	private String name;
	@DtoField("leadingSymbol")
	private String leadingSymbol;
	@DtoField(value = "createdAt", readOnly = true)
	private Date createdAt;
	@DtoField(value = "createdBy.employeeId", readOnly = true, entityBeanKeys = {"Employee"})
	private Integer createdById;
	@DtoField(value = "updatedAt", readOnly = true)
	private Date updatedAt;
	@DtoField(value = "updatedBy.employeeId", readOnly = true, entityBeanKeys = {"Employee"})
	private Integer updatedById;
}