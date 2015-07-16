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
@Table(name = "currency", schema = "public")
@SequenceGenerator(name = "seq_currency_currency_id", sequenceName = "seq_currency_currency_id", 
		allocationSize = 1)
public class Currency extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_currency_currency_id")
	@Column(name = "currency_id")
	private Short currencyId;
	@Column(name = "name")
	private String name;
	@Column(name = "abbreviation")
	private String abbreviation;
	@Column(name = "symbol")
	private String symbol;
	@Column(name = "launched_on")
	private Date launchedOn;
	@Column(name = "block_time")
	private Integer blockTime;
	@Column(name = "standard_fee")
	private BigDecimal standardFee;
	@Column(name = "website_url")
	private String websiteUrl;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	private Employee updatedBy;
}