package com.neemre.bitplexus.backend.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@SequenceGenerator(name = "seq_transaction_id", sequenceName = "seq_transactions_transaction_id", 
		allocationSize = 1)
public class Transaction extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transaction_id")
	@Column(name = "transaction_id", insertable = false, updatable = false)
	private Long transactionId;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "transaction_status_type_id")
	private TransactionStatusType transactionStatusType;
	@Column(name = "local_uid", insertable = false, updatable = false)
	private String localUid;
	@Column(name = "network_uid", updatable = false)
	private String networkUid;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "received_at")
	private Date receivedAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "confirmed_at", insertable = false)
	private Date confirmedAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "completed_at", insertable = false)
	private Date completedAt;
	@Column(name = "block_height", insertable = false)
	private Integer blockHeight;
	@Column(name = "binary_size", updatable = false)
	private Integer binarySize;
	@Column(name = "fee", updatable = false)
	private BigDecimal fee;
	@Column(name = "unit_price", updatable = false)
	private BigDecimal unitPrice;
	@Column(name = "note", updatable = false)
	private String note;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logged_at", insertable = false, updatable = false)
	private Date loggedAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
}