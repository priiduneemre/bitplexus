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
import com.neemre.bitplexus.backend.model.enums.TransactionEndpointTypes;
import com.neemre.bitplexus.common.dto.virtual.TransactionTypeDto;

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
	private TransactionTypeDto transactionType;
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
	private Integer confirmations;
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


	public List<TransactionEndpointDto> getTransactionInputs() {
		List<TransactionEndpointDto> transactionInputs = new ArrayList<TransactionEndpointDto>();
		for (TransactionEndpointDto transactionEndpoint : transactionEndpoints) {
			if (transactionEndpoint.getTransactionEndpointType().getCode().equals(
					TransactionEndpointTypes.INPUT.name())) {
				transactionInputs.add(transactionEndpoint);
			}
		}
		return transactionInputs;
	}

	public BigDecimal getTotalInput() {
		BigDecimal inputSum = BigDecimal.ZERO;
		for (TransactionEndpointDto transactionInput : getTransactionInputs()) {
			inputSum = inputSum.add(transactionInput.getAmount());
		}
		return inputSum;
	}

	public List<TransactionEndpointDto> getTransactionOutputs() {
		List<TransactionEndpointDto> transactionOutputs = new ArrayList<TransactionEndpointDto>();
		for (TransactionEndpointDto transactionEndpoint : transactionEndpoints) {
			if (transactionEndpoint.getTransactionEndpointType().getCode().equals(
					TransactionEndpointTypes.OUTPUT_MAIN.name())) {
				transactionOutputs.add(transactionEndpoint);
			} else if (transactionEndpoint.getTransactionEndpointType().getCode().equals(
					TransactionEndpointTypes.OUTPUT_CHANGE.name())) {
				transactionOutputs.add(transactionEndpoint);
			}
		}
		return transactionOutputs;
	}

	public BigDecimal getTotalOutput() {
		BigDecimal outputSum = BigDecimal.ZERO;
		for (TransactionEndpointDto transactionOutput : getTransactionOutputs()) {
			outputSum = outputSum.add(transactionOutput.getAmount());
		}
		return outputSum;
	}

	public BigDecimal getAmount() {
		BigDecimal mainOutputSum = BigDecimal.ZERO;
		for (TransactionEndpointDto transactionEndpoint : transactionEndpoints) {
			if (transactionEndpoint.getTransactionEndpointType().getCode().equals(
					TransactionEndpointTypes.OUTPUT_MAIN.name())) {
				mainOutputSum = mainOutputSum.add(transactionEndpoint.getAmount());
			}
		}
		return mainOutputSum;
	}

	public static class TransactionEndpointDtoToTransactionEndpointMatcher implements 
			DtoToEntityMatcher<TransactionEndpointDto, TransactionEndpoint> {

		@Override
		public boolean match(final TransactionEndpointDto dto, final TransactionEndpoint entity) {
			return dto.getTransactionEndpointId().equals(entity.getTransactionEndpointId());
		}
	}
}