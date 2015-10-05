package com.neemre.bitplexus.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Dto("com.neemre.bitplexus.backend.model.Currency")
public class CurrencyDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "currencyId", readOnly = true)
	private Short currencyId;
	@DtoField("name")
	private String name;
	@DtoField("abbreviation")
	private String abbreviation;
	@DtoField("symbol")
	private String symbol;
	@DtoField("launchedOn")
	private Date launchedOn;
	@DtoField("blockTime")
	private Integer blockTime;
	@DtoField("standardFee")
	private BigDecimal standardFee;
	@DtoField("websiteUrl")
	private String websiteUrl;
	@DtoField(value = "createdAt", readOnly = true)
	private Date createdAt;
	@DtoField(value = "updatedAt", readOnly = true)
	private Date updatedAt;
	@DtoField(value = "updatedBy.employeeId", readOnly = true, entityBeanKeys = {"Employee"})
	private Integer updatedById;
}