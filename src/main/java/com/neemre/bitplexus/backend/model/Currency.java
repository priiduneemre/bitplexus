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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@Table(name = "currency", schema = "public")
@SequenceGenerator(name = "seq_currency_id", sequenceName = "seq_currency_currency_id", 
		allocationSize = 1)
public class Currency extends BaseEntity {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_currency_id")
	@Column(name = "currency_id", insertable = false, updatable = false)
	private Short currencyId;
	@NotNull
	@Size(max = 25)
	@Column(name = "name")
	private String name;
	@NotNull
	@Size(max = 8)
	@Column(name = "abbreviation")
	private String abbreviation;
	@Size(max = 3)
	@Column(name = "symbol")
	private String symbol;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "launched_on", updatable = false)
	private Date launchedOn;
	@NotNull
	@Min(1)
	@Max(86400)
	@Column(name = "block_time")
	private Integer blockTime;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin("0")
	@Column(name = "standard_fee")
	private BigDecimal standardFee;
	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "website_url")
	private String websiteUrl;
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", insertable = false, updatable = false)
	private Date createdAt;
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "updated_by", insertable = false)
	private Employee updatedBy;
}