package com.neemre.bitplexus.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
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
@Table(name = "member", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "seq_member_id", sequenceName = "seq_member_member_id", allocationSize = 1)
@NamedStoredProcedureQuery(name = "findMemberRolesByUsername", 
		procedureName = "f_get_member_roles", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_username", type = String[].class)})
public class Member extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member_id")
	@Column(name = "member_id", insertable = false, updatable = false)
	private Integer memberId;
	@NotNull 
	@Size(max = 20)
	@Column(name = "username", updatable = false)
	private String username;
	@NotNull
	@Size(min = 60, max = 60)
	@Pattern(regexp = "^\\$2a\\$12\\$.{53}$")
	@Column(name = "password", updatable = false)
	private String password;
	@NotNull
	@Size(max = 60)
	@Column(name = "email_address")
	private String emailAddress;
	@NotNull
	@Size(min = 8, max = 15)
	@Pattern(regexp = "^[0-9]*$")
	@Column(name = "phone_number")
	private String phoneNumber;
	@Min(0)
	@Generated(GenerationTime.INSERT)
	@Column(name = "failed_logins", insertable = false)
	private Short failedLogins;
	@NotNull
	@Column(name = "is_active")
	private Boolean isActive;
	@Past
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registered_at", insertable = false, updatable = false)
	private Date registeredAt;
	@Past
	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "updated_by", insertable = false)
	private Employee updatedBy;
}