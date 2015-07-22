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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "payment_request", schema = "public")
@SequenceGenerator(name = "seq_payment_request_id", 
		sequenceName = "seq_payment_request_payment_request_id", allocationSize = 1)
public class PaymentRequest extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_payment_request_id")
	@Column(name = "payment_request_id", insertable = false, updatable = false)
	private Long paymentRequestId;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_id", updatable = false)
	private Address address;
	@Column(name = "amount", updatable = false)
	private BigDecimal amount;
	@Column(name = "message", updatable = false)
	private String message;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "requested_at", insertable = false, updatable = false)
	private Date requestedAt;
}