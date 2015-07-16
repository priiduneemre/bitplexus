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
@Table(name = "chain", schema = "public")
@SequenceGenerator(name = "seq_chain_chain_id", sequenceName = "seq_chain_chain_id", 
		allocationSize = 1)
public class Chain extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_chain_chain_id")
	@Column(name = "chain_id")
	private Short chainId;
	private Currency currency;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "started_on")
	private Date startedOn;
	@Column(name = "available_supply")
	private BigDecimal availableSupply;
	@Column(name = "is_operational")
	private Boolean isOperational;
	@Column(name = "created_at")
	private Date createdAt;
	private Employee createdBy;
	@Column(name = "updated_at")
	private Date updatedAt;
	private Employee updatedBy;
}