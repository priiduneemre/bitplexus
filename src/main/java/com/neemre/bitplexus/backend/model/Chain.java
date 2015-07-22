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
@Table(name = "chain", schema = "public")
@SequenceGenerator(name = "seq_chain_id", sequenceName = "seq_chain_chain_id", allocationSize = 1)
public class Chain extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_chain_id")
	@Column(name = "chain_id", insertable = false, updatable = false)
	private Short chainId;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "currency_id", updatable = false)
	private Currency currency;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Temporal(TemporalType.DATE)
	@Column(name = "started_on", updatable = false)
	private Date startedOn;
	@Column(name = "available_supply")
	private BigDecimal availableSupply;
	@Column(name = "is_operational")
	private Boolean isOperational;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", insertable = false, updatable = false)
	private Date createdAt;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "created_by", updatable = false)
	private Employee createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "updated_by", insertable = false)
	private Employee updatedBy;
}