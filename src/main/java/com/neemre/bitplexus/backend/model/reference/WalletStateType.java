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
@Table(name = "wallet_state_type", schema = "public")
public class WalletStateType extends ReferenceEntity {
	
	@Id
	@Column(name = "wallet_state_type_id")
	private Short walletStateTypeId;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
}