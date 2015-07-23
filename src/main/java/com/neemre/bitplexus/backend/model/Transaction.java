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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transaction_id")
	@Column(name = "transaction_id", insertable = false, updatable = false)
	private Long transactionId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "transaction_status_type_id")
	private TransactionStatusType transactionStatusType;
	@Size(min = 36, max = 36)
	@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
	@Column(name = "local_uid", insertable = false, updatable = false)
	private String localUid;
	@NotNull
	@Size(min = 64, max = 64)
	@Pattern(regexp = "^[0-9a-f]*$")
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
	@Min(0)
	@Column(name = "block_height", insertable = false)
	private Integer blockHeight;
	@NotNull
	@Min(1)
	@Max(1000000)
	@Column(name = "binary_size", updatable = false)
	private Integer binarySize;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin("0")
	@Column(name = "fee", updatable = false)
	private BigDecimal fee;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin(value = "0", inclusive = false)
	@Column(name = "unit_price", updatable = false)
	private BigDecimal unitPrice;
	@Size(max = 255)
	@Column(name = "note", updatable = false)
	private String note;
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logged_at", insertable = false, updatable = false)
	private Date loggedAt;
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
}