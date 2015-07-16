package com.neemre.bitplexus.backend.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Entity
@Table(name = "transactions", schema = "public")
@SequenceGenerator(name = "seq_transactions_transaction_id", 
	sequenceName = "seq_transactions_transaction_id", allocationSize = 1)
public class Transaction extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transactions_transaction_id")
	@Column(name = "transaction_id")
	private Long transactionId;
	private TransactionStatusType transactionStatusType;
	@Column(name = "local_uid")
	private String localUid;
	@Column(name = "network_uid")
	private String networkUid;
	@Column(name = "received_at")
	private Date receivedAt;
	@Column(name = "confirmed_at")
	private Date confirmedAt;
	@Column(name = "completed_at")
	private Date completedAt;
	@Column(name = "block_height")
	private Integer blockHeight;
	@Column(name = "binary_size")
	private Integer binarySize;
	@Column(name = "fee")
	private BigDecimal fee;
	@Column(name = "unit_price")
	private BigDecimal unitPrice;
	@Column(name = "note")
	private String note;
	@Column(name = "logged_at")
	private Date loggedAt;
	@Column(name = "updated_at")
	private Date updatedAt;
}