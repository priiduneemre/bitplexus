package com.neemre.bitplexus.backend.model;

import java.util.Date;

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
public class AddressType extends Entity {
	
	private Short addressTypeId;
	private Chain chain;
	private String code;
	private String name;
	private Character leadingSymbol;
	private Date createdAt;
	private Employee createdBy;
	private Date updatedAt;
	private Employee updatedBy;
}