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
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_chain_id")
	@Column(name = "chain_id", insertable = false, updatable = false)
	private Short chainId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "currency_id", updatable = false)
	private Currency currency;
	@NotNull
	@Size(max = 30)
	@Column(name = "code")
	private String code;
	@NotNull
	@Size(max = 60)
	@Column(name = "name")
	private String name;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "started_on", updatable = false)
	private Date startedOn;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin("0")
	@Column(name = "available_supply")
	private BigDecimal availableSupply;
	@NotNull
	@Column(name = "is_operational")
	private Boolean isOperational;
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", insertable = false, updatable = false)
	private Date createdAt;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "created_by", updatable = false)
	private Employee createdBy;
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "updated_by", insertable = false)
	private Employee updatedBy;
}