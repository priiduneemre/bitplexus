package com.neemre.bitplexus.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
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
@Table(name = "employee", schema = "public")
@PrimaryKeyJoinColumn(name = "employee_id")
public class Employee extends Person {

	@NotNull
	@Column(name = "employee_id", insertable = false, updatable = false)
	private Integer employeeId;
	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(name = "born_on", updatable = false)
	private Date bornOn;
	@NotNull
	@Size(min = 5, max = 34)
	@Pattern(regexp = "^[0-9A-Z]*$")
	@Column(name = "iban")
	private String iban;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "employed_on", updatable = false)
	private Date employedOn;
	@Temporal(TemporalType.DATE)
	@Column(name = "resigned_on", insertable = false)
	private Date resignedOn;
	@NotNull
	@Column(name = "is_active")
	private Boolean isActive;
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", insertable = false, updatable = false)
	private Date createdAt;
}