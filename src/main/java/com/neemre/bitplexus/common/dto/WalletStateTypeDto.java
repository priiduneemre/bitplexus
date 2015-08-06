package com.neemre.bitplexus.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Dto("com.neemre.bitplexus.backend.model.reference.WalletStateType")
public class WalletStateTypeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "walletStateTypeId", readOnly = true)
	private Short walletStateTypeId;
	@DtoField(value = "code", readOnly = true)
	private String code;
	@DtoField(value = "name", readOnly = true)
	private String name;
}