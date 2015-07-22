package com.neemre.bitplexus.backend.model.reference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "address_state_type", schema = "public")
public class AddressStateType extends ReferenceEntity {

	@Id
	@Column(name = "address_state_type_id", insertable = false, updatable = false)
	private Short addressStateTypeId;
	@Column(name = "code", insertable = false, updatable = false)
	private String code;
	@Column(name = "name", insertable = false, updatable = false)
	private String name;
}