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
public class AddressBookEntry extends Entity {
	
	private Long addressBookEntryId;
	private Customer customer;
	private Address address;
	private String label;
	private Date createdAt;
	private Date updatedAt;
}