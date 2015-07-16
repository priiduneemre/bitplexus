package com.neemre.bitplexus.backend.model;

import java.util.Date;

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
@Table(name = "employee", schema = "public")
public class Employee extends Person {

	@Id
	@Column(name = "employee_id")
	private Integer employeeId;
	@Column(name = "born_on")
	private Date bornOn;
	@Column(name = "iban")
	private String iban;
	@Column(name = "employed_on")
	private Date employedOn;
	@Column(name = "resigned_on")
	private Date resignedOn;
	@Column(name = "is_active")
	private Boolean isActive;
	@Column(name = "created_at")
	private Date createdAt;
}