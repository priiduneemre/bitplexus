package com.neemre.bitplexus.backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"employeeRoles"})
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "employee", schema = "public")
@PrimaryKeyJoinColumn(name = "employee_id")
public class Employee extends Person {

	private static final long serialVersionUID = 1L;
	
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
	
	@Setter(AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	@OrderBy("isActive DESC")
	private List<EmployeeRole> employeeRoles = new ArrayList<EmployeeRole>();
	
	
	public List<EmployeeRole> getEmployeeRoles() {
		return Collections.unmodifiableList(employeeRoles);
	}

	public void addEmployeeRole(EmployeeRole employeeRole) {
		if (employeeRole != null) {
			if (!employeeRoles.contains(employeeRole)) {
				employeeRoles.add(employeeRole);
				Collections.sort(employeeRoles, EmployeeRole.NATURAL_ORDERING);
				employeeRole.setEmployee(this);
			}
		}
	}

	public void removeEmployeeRole(EmployeeRole employeeRole) {
		if (employeeRole != null) {
			if (employeeRoles.contains(employeeRole)) {
				employeeRoles.remove(employeeRole);
				employeeRole.setEmployee(null);
			}
		}
	}
}