package com.neemre.bitplexus.backend.model;

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
@Table(name = "address_type", schema = "public")
@SequenceGenerator(name = "seq_address_type_address_type_id", 
		sequenceName = "seq_address_type_address_type_id", allocationSize = 1)
public class AddressType extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_type_address_type_id")
	@Column(name = "address_type_id")
	private Short addressTypeId;
	private Chain chain;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "leading_symbol")
	private Character leadingSymbol;
	@Column(name = "created_at")
	private Date createdAt;
	private Employee createdBy;
	@Column(name = "updated_at")
	private Date updatedAt;
	private Employee updatedBy;
}