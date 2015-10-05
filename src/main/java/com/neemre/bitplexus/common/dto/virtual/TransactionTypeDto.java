package com.neemre.bitplexus.common.dto.virtual;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 30)
	private String code;
	@NotNull
	@Size(max = 60)
	private String name;
}