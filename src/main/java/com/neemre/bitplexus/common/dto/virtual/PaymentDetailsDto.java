package com.neemre.bitplexus.common.dto.virtual;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 26, max = 35)
	@Pattern(regexp = "^[1-9a-km-zA-HJ-NP-Z]*$")
	private String recipientAddress;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin(value = "0", inclusive = false)
	private BigDecimal amount;
	@Size(max = 255)
	private String note;
}