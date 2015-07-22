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

import com.neemre.bitplexus.backend.model.reference.AddressStateType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "address", schema = "public")
@SequenceGenerator(name = "seq_address_id", sequenceName = "seq_address_address_id", 
		allocationSize = 1)
public class Address extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_id")
	@Column(name = "address_id", insertable = false, updatable = false)
	private Long addressId;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "wallet_id", updatable = false)
	private Wallet wallet;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_type_id", updatable = false)
	private AddressType addressType;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_state_type_id", insertable = false)
	private AddressStateType addressStateType;
	@Column(name = "label")
	private String label;
	@Column(name = "encoded_form", updatable = false)
	private String encodedForm;
	@Column(name = "balance")
	private BigDecimal balance;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "indexed_at", insertable = false, updatable = false)
	private Date indexedAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
}