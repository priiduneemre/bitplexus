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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.neemre.bitplexus.backend.model.reference.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "employee_role", schema = "public")
@SequenceGenerator(name = "seq_employee_role_id", sequenceName = "seq_employee_role_employee_role_id",
		allocationSize = 1)
public class EmployeeRole extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee_role_id")
	@Column(name = "employee_role_id", insertable = false, updatable = false)
	private Integer employeeRoleId;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "employee_id", updatable = false)
	private Employee employee;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "role_id", updatable = false)
	private Role role;
	@Column(name = "is_active", insertable = false)
	private Boolean isActive;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "assigned_at", insertable = false, updatable = false)
	private Date assignedAt;
}