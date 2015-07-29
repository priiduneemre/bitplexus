package com.neemre.bitplexus.backend.model.reference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@Table(name = "transaction_endpoint_type", schema = "public")
public class TransactionEndpointType extends ReferenceEntity {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@Column(name = "transaction_endpoint_type_id", insertable = false, updatable = false)
	private Short transactionEndpointTypeId;
	@NotNull
	@Size(max = 30)
	@Column(name = "code", insertable = false, updatable = false)
	private String code;
	@NotNull
	@Size(max = 60)
	@Column(name = "name", insertable = false, updatable = false)
	private String name;
}