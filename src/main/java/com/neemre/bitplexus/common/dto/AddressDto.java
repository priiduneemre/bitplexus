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
@Dto("com.neemre.bitplexus.backend.model.Address")
public class AddressDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "addressId", readOnly = true)
	private Long addressId;
	@DtoField(value = "wallet.walletId", readOnly = true, entityBeanKeys = {"Wallet"})
	private Integer walletId;
	@DtoField(value = "addressType", readOnly = true, dtoBeanKey = "AddressTypeDto", 
			entityBeanKeys = {"AddressType"})
	private AddressTypeDto addressType;
	@DtoField(value = "addressStateType", readOnly = true, dtoBeanKey = "AddressStateTypeDto", 
			entityBeanKeys = {"AddressStateType"})
	private AddressStateTypeDto addressStateType;
	@DtoField("label")
	private String label;
	@DtoField(value = "encodedForm", readOnly = true)
	private String encodedForm;
	@DtoField(value = "balance", readOnly = true)
	private BigDecimal balance;
	private Integer transactionCount;
	private BigDecimal totalReceived;
	private BigDecimal totalSent;
	@DtoField(value = "indexedAt", readOnly = true)
	private Date indexedAt;
	@DtoField(value = "updatedAt", readOnly = true)
	private Date updatedAt;
}