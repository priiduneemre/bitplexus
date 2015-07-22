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
@Table(name = "transaction_endpoint_type", schema = "public")
public class TransactionEndpointType extends ReferenceEntity {

	@Id
	@Column(name = "transaction_endpoint_type_id", insertable = false, updatable = false)
	private Short transactionEndpointTypeId;
	@Column(name = "code", insertable = false, updatable = false)
	private String code;
	@Column(name = "name", insertable = false, updatable = false)
	private String name;
}