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

import com.neemre.bitplexus.backend.model.reference.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "employee_role", schema = "public")
@SequenceGenerator(name = "seq_employee_role_employee_role_id", 
	sequenceName = "seq_employee_role_employee_role_id", allocationSize = 1)
public class EmployeeRole extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee_role_employee_role_id")
	@Column(name = "employee_role_id")
	private Integer employeeRoleId;
	private Employee employee;
	private Role role;
	@Column(name = "is_active")
	private Boolean isActive;
	@Column(name = "assigned_at")
	private Date assignedAt;
}