package com.neemre.bitplexus.backend.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.neemre.bitplexus.backend.model.reference.TransactionStatusType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Transaction extends Entity {
	
	private Long transactionId;
	private TransactionStatusType transactionStatusType;
	private String localUid;
	private String networkUid;
	private Date receivedAt;
	private Date confirmedAt;
	private Date completedAt;
	private Integer blockHeight;
	private Integer binarySize;
	private BigDecimal fee;
	private BigDecimal unitPrice;
	private String note;
	private Date loggedAt;
	private Date updatedAt;
}