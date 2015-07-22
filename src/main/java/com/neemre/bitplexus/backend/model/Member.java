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
import javax.persistence.SequenceGenerator;
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
@Table(name = "member", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "seq_member_id", sequenceName = "seq_member_member_id", allocationSize = 1)
public class Member extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member_id")
	@Column(name = "member_id", insertable = false, updatable = false)
	private Integer memberId;
	@Column(name = "username", updatable = false)
	private String username;
	@Column(name = "password", updatable = false)
	private String password;
	@Column(name = "email_address")
	private String emailAddress;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "failed_logins", insertable = false)
	private Short failedLogins;
	@Column(name = "is_active")
	private Boolean isActive;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registered_at", insertable = false, updatable = false)
	private Date registeredAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "updated_by", insertable = false)
	private Employee updatedBy;
}