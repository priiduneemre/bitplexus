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
@Dto("com.neemre.bitplexus.backend.model.TransactionEndpoint")
public class TransactionEndpointDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "transactionEndpointId", readOnly = true)
	private Long transactionEndpointId;
	@DtoField(value = "transaction.transactionId", readOnly = true, entityBeanKeys = {"Transaction"})
	private Long transactionId;
	@DtoField(value = "address.addressId", readOnly = true, entityBeanKeys = {"Address"})
	private Long addressId;
	@DtoField(value = "address.encodedForm", readOnly = true, entityBeanKeys = {"Address"})
	private String encodedForm;
	@DtoField(value = "transactionEndpointType", readOnly = true, 
			dtoBeanKey = "TransactionEndpointTypeDto", entityBeanKeys = {"TransactionEndpointType"})
	private TransactionEndpointTypeDto transactionEndpointType;
	@DtoField(value = "amount", readOnly = true)
	private BigDecimal amount;
	@DtoField(value = "loggedAt", readOnly = true)
	private Date loggedAt;
}