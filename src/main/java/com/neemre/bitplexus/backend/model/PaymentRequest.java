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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

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

	public static final Ordering<PaymentRequest> CREATION_TIME_ORDERING = Ordering.natural().reverse()
			.nullsLast().onResultOf(new CreationTimeExtractor());
	public static final Ordering<PaymentRequest> NATURAL_ORDERING = CREATION_TIME_ORDERING;
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_payment_request_id")
	@Column(name = "payment_request_id", insertable = false, updatable = false)
	private Long paymentRequestId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_id", updatable = false)
	private Address address;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin(value = "0", inclusive = false)
	@Column(name = "amount", updatable = false)
	private BigDecimal amount;
	@Size(max = 255)
	@Column(name = "message", updatable = false)
	private String message;
	@Past
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "requested_at", insertable = false, updatable = false)
	private Date requestedAt;
	
	
	public void setAddress(Address address) {
		if (this.address != address) {
			if (this.address != null) {
				this.address.removePaymentRequest(this);
			}
			this.address = address;
			if (address != null) {
				address.addPaymentRequest(this);
			} 
		} 
	}
	
	private static class CreationTimeExtractor implements Function<PaymentRequest, Date> {

		@Override
		public Date apply(PaymentRequest paymentRequest) {
			return paymentRequest.getRequestedAt();
		}
	}
}