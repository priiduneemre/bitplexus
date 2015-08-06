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
@Dto("com.neemre.bitplexus.backend.model.Wallet")
public class WalletDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "walletId", readOnly = true)
	private Integer walletId;
	@DtoField(value = "customer.username", readOnly = true, entityBeanKeys = {"Customer"})
	private String username;
	@DtoField(value = "walletStateType", readOnly = true, dtoBeanKey = "WalletStateTypeDto", 
			entityBeanKeys = {"WalletStateType"})
	private WalletStateTypeDto walletStateType;
	@DtoField("name")
	private String name;
	@DtoField(value = "createdAt", readOnly = true)
	private Date createdAt;
	@DtoField(value = "updatedAt", readOnly = true)
	private Date updatedAt;
}