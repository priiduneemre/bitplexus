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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "payment_request", schema = "public")
@SequenceGenerator(name = "seq_payment_request_payment_request_id", 
		sequenceName = "seq_payment_request_payment_request_id", allocationSize = 1)
public class PaymentRequest extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_payment_request_payment_request_id")
	@Column(name = "payment_request_id")
	private Long paymentRequestId;
	private Address address;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "message")
	private String message;
	@Column(name = "requested_at")
	private Date requestedAt;
}