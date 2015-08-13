package com.neemre.bitplexus.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inspiresoftware.lib.dto.geda.adapter.DtoToEntityMatcher;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoCollection;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;
import com.neemre.bitplexus.backend.model.TransactionEndpoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Dto("com.neemre.bitplexus.backend.model.Transaction")
public class TransactionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "transactionId", readOnly = true)
	private Long transactionId;
	@DtoField(value = "transactionStatusType", readOnly = true, dtoBeanKey = "TransactionStatusTypeDto",
			entityBeanKeys = {"TransactionStatusType"})
	private TransactionStatusTypeDto transactionStatusType;
	@DtoField(value = "localUid", readOnly = true)
	private String localUid;
	@DtoField(value = "networkUid", readOnly = true)
	private String networkUid;
	@DtoField("receivedAt")
	private Date receivedAt;
	@DtoField("confirmedAt")
	private Date confirmedAt;
	@DtoField("completedAt")
	private Date completedAt;
	@DtoField("blockHeight")
	private Integer blockHeight;
	@DtoField(value = "binarySize", readOnly = true)
	private Integer binarySize;
	@DtoField(value = "fee", readOnly = true)
	private BigDecimal fee;
	@DtoField(value = "unitPrice", readOnly = true)
	private BigDecimal unitPrice;
	@DtoField("note")
	private String note;
	@DtoField(value = "loggedAt", readOnly = true)
	private Date loggedAt;
	@DtoField(value = "updatedAt", readOnly = true)
	private Date updatedAt;

	@DtoCollection(value = "transactionEndpoints", readOnly = true,
			dtoCollectionClass = ArrayList.class, entityCollectionClass = ArrayList.class,
			dtoBeanKey = "TransactionEndpointDto", entityBeanKeys = {"TransactionEndpoint"},
			dtoToEntityMatcher = TransactionEndpointDtoToTransactionEndpointMatcher.class)
	private List<TransactionEndpointDto> transactionEndpoints;
	


	public static class TransactionEndpointDtoToTransactionEndpointMatcher implements 
			DtoToEntityMatcher<TransactionEndpointDto, TransactionEndpoint> {

		@Override
		public boolean match(final TransactionEndpointDto dto, final TransactionEndpoint entity) {
			return dto.getTransactionEndpointId().equals(entity.getTransactionEndpointId());
		}
	}
}