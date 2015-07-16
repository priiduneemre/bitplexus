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

import com.neemre.bitplexus.backend.model.reference.TransactionEndpointType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "transaction_endpoint", schema = "public")
@SequenceGenerator(name = "seq_transaction_endpoint_transaction_endpoint_id", 
		sequenceName = "seq_transaction_endpoint_transaction_endpoint_id", allocationSize = 1)
public class TransactionEndpoint extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transaction_endpoint_transaction_endpoint_id")
	@Column(name = "transaction_endpoint_id")
	private Long transactionEndpointId;
	private Transaction transaction;
	private Address address;
	private TransactionEndpointType transactionEndpointType;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "logged_at")
	private Date loggedAt;
}