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

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.neemre.bitplexus.backend.model.reference.TransactionEndpointType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "transaction_endpoint", schema = "public")
@SequenceGenerator(name = "seq_transaction_endpoint_id", 
		sequenceName = "seq_transaction_endpoint_transaction_endpoint_id", allocationSize = 1)
public class TransactionEndpoint extends BaseEntity {
	
	public static final Ordering<TransactionEndpoint> TRANSACTION_ENDPOINT_TYPE_ORDERING = 
			Ordering.natural().nullsLast().onResultOf(new TransactionEndpointTypeExtractor());
	public static final Ordering<TransactionEndpoint> NATURAL_ORDERING = 
			TRANSACTION_ENDPOINT_TYPE_ORDERING;
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transaction_endpoint_id")
	@Column(name = "transaction_endpoint_id", insertable = false, updatable = false)
	private Long transactionEndpointId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "transaction_id", updatable = false)
	private Transaction transaction;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_id", updatable = false)
	private Address address;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "transaction_endpoint_type_id", updatable = false)
	private TransactionEndpointType transactionEndpointType;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin(value = "0", inclusive = false)
	@Column(name = "amount", updatable = false)
	private BigDecimal amount;
	@Past
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logged_at", insertable = false, updatable = false)
	private Date loggedAt;
	
	
	public void setTransaction(Transaction transaction) {
		if (this.transaction != transaction) {
			if (this.transaction != null) {
				this.transaction.removeTransactionEndpoint(this);
			}
			this.transaction = transaction;
			if (transaction != null) {
				transaction.addTransactionEndpoint(this);
			} 
		} 
	}
	
	private static class TransactionEndpointTypeExtractor implements Function<TransactionEndpoint, Short> {
		
		@Override
		public Short apply(TransactionEndpoint transactionEndpoint) {
			if (transactionEndpoint.getTransactionEndpointType() != null) {
				return transactionEndpoint.getTransactionEndpointType().getTransactionEndpointTypeId();
			}
			return null;
		}
	}
}