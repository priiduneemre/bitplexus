package com.neemre.bitplexus.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.google.common.base.Function;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Dto("com.neemre.bitplexus.backend.model.Chain")
public class ChainDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "chainId", readOnly = true)
	private Short chainId;
	@DtoField(value = "currency.currencyId", readOnly = true, entityBeanKeys = {"Currency"})
	private Short currencyId;
	@DtoField("code")
	private String code;
	@DtoField("name")
	private String name;
	@DtoField("startedOn")
	private Date startedOn;
	@DtoField("availableSupply")
	private BigDecimal availableSupply;
	@DtoField("isOperational")
	private Boolean isOperational;
	@DtoField(value = "createdAt", readOnly = true)
	private Date createdAt;
	@DtoField(value = "createdBy.employeeId", readOnly = true, entityBeanKeys = {"Employee"})
	private Integer createdById;
	@DtoField(value = "updatedAt", readOnly = true)
	private Date updatedAt;
	@DtoField(value = "updatedBy.employeeId", readOnly = true, entityBeanKeys = {"Employee"})
	private Integer updatedById;


	public static class CodeExtractor implements Function<ChainDto, String> {

		@Override
		public String apply(ChainDto chainDto) {
			return chainDto.getCode();
		}
	}
}