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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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
@SequenceGenerator(name = "seq_address_book_entry_id", 
		sequenceName = "seq_address_book_entry_address_book_entry_id", allocationSize = 1)
public class AddressBookEntry extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_book_entry_id")
	@Column(name = "address_book_entry_id", insertable = false, updatable = false)
	private Long addressBookEntryId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", updatable = false)
	private Customer customer;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_id", updatable = false)
	private Address address;
	@NotNull
	@Size(max = 60)
	@Column(name = "label")
	private String label;
	@Past
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", insertable = false, updatable = false)
	private Date createdAt;
	@Past
	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
}