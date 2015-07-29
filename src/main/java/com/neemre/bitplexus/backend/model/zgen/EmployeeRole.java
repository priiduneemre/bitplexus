package com.neemre.bitplexus.backend.model.zgen;

// Generated Jul 15, 2015 5:58:31 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * EmployeeRole generated by hbm2java
 */
public class EmployeeRole implements java.io.Serializable {

	private int employeeRoleId;
	private Employee employee;
	private Role role;
	private boolean isActive;
	private Date assignedAt;

	public EmployeeRole() {
	}

	public EmployeeRole(int employeeRoleId, Employee employee, Role role,
			boolean isActive, Date assignedAt) {
		this.employeeRoleId = employeeRoleId;
		this.employee = employee;
		this.role = role;
		this.isActive = isActive;
		this.assignedAt = assignedAt;
	}

	public int getEmployeeRoleId() {
		return this.employeeRoleId;
	}

	public void setEmployeeRoleId(int employeeRoleId) {
		this.employeeRoleId = employeeRoleId;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getAssignedAt() {
		return this.assignedAt;
	}

	public void setAssignedAt(Date assignedAt) {
		this.assignedAt = assignedAt;
	}

}
