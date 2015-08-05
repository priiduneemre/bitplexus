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

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
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

	public static final Ordering<EmployeeRole> ACTIVENESS_ORDERING = Ordering.natural().reverse()
			.nullsLast().onResultOf(new ActivenessExtractor());
	public static final Ordering<EmployeeRole> NATURAL_ORDERING = ACTIVENESS_ORDERING;
	private static final long serialVersionUID = 1L;

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee_role_id")
	@Column(name = "employee_role_id", insertable = false, updatable = false)
	private Integer employeeRoleId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "employee_id", updatable = false)
	private Employee employee;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "role_id", updatable = false)
	private Role role;
	@Generated(GenerationTime.INSERT)
	@Column(name = "is_active", insertable = false)
	private Boolean isActive;
	@Past
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "assigned_at", insertable = false, updatable = false)
	private Date assignedAt;


	public void setEmployee(Employee employee) {
		if (this.employee != employee) {
			if (this.employee != null) {
				this.employee.removeEmployeeRole(this);
			}
			this.employee = employee;
			if (employee != null) {
				employee.addEmployeeRole(this);
			} 
		} 
	}

	private static class ActivenessExtractor implements Function<EmployeeRole, Boolean> {

		@Override
		public Boolean apply(EmployeeRole employeeRole) {
			return employeeRole.getIsActive();
		}
	}
}