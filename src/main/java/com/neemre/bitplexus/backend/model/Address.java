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

import com.neemre.bitplexus.backend.model.reference.AddressStateType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "address", schema = "public")
@SequenceGenerator(name = "seq_address_address_id", sequenceName = "seq_address_address_id", 
		allocationSize = 1)
public class Address extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_address_id")
	@Column(name = "address_id")
	private Long addressId;
	private Wallet wallet;
	private AddressType addressType;
	private AddressStateType addressStateType;
	@Column(name = "label")
	private String label;
	@Column(name = "encoded_form")
	private String encodedForm;
	@Column(name = "balance")
	private BigDecimal balance;
	@Column(name = "indexed_at")
	private Date indexedAt;
	@Column(name = "updated_at")
	private Date updatedAt;
}