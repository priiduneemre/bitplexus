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
@Table(name = "address_book_entry", schema = "public")
@SequenceGenerator(name = "seq_address_book_entry_address_book_entry_id", 
	sequenceName = "seq_address_book_entry_address_book_entry_id", allocationSize = 1)
public class AddressBookEntry extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_book_entry_address_book_entry_id")
	@Column(name = "address_book_entry_id")
	private Long addressBookEntryId;
	private Customer customer;
	private Address address;
	@Column(name = "label")
	private String label;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
}