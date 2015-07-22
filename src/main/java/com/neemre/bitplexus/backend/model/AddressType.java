package com.neemre.bitplexus.backend.model;

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
@Table(name = "address_type", schema = "public")
@SequenceGenerator(name = "seq_address_type_id", sequenceName = "seq_address_type_address_type_id",
		allocationSize = 1)
public class AddressType extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_type_id")
	@Column(name = "address_type_id", insertable = false, updatable = false)
	private Short addressTypeId;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "chain_id", updatable = false)
	private Chain chain;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "leading_symbol", updatable = false)
	private Character leadingSymbol;
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