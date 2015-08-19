package com.neemre.bitplexus.common.dto.virtual;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String recipientAddress;
	private BigDecimal amount;
	private String note;
}