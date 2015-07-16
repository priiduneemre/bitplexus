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
@Table(name = "transaction_status_type", schema = "public")
public class TransactionStatusType extends ReferenceEntity {
	
	@Id
	@Column(name = "transaction_status_type_id")
	private Short transactionStatusTypeId;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
}