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
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transaction_endpoint_id")
	@Column(name = "transaction_endpoint_id", insertable = false, updatable = false)
	private Long transactionEndpointId;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "transaction_id", updatable = false)
	private Transaction transaction;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_id", updatable = false)
	private Address address;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "transaction_endpoint_type_id", updatable = false)
	private TransactionEndpointType transactionEndpointType;
	@Column(name = "amount", updatable = false)
	private BigDecimal amount;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logged_at", insertable = false, updatable = false)
	private Date loggedAt;
}