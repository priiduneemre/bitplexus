package com.neemre.bitplexus.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Column(name = "employee_id", insertable = false, updatable = false)
	private Integer employeeId;
	@Temporal(TemporalType.DATE)
	@Column(name = "born_on", updatable = false)
	private Date bornOn;
	@Column(name = "iban")
	private String iban;
	@Temporal(TemporalType.DATE)
	@Column(name = "employed_on", updatable = false)
	private Date employedOn;
	@Temporal(TemporalType.DATE)
	@Column(name = "resigned_on", insertable = false)
	private Date resignedOn;
	@Column(name = "is_active")
	private Boolean isActive;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", insertable = false, updatable = false)
	private Date createdAt;
}