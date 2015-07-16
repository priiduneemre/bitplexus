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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "member", schema = "public")
@SequenceGenerator(name = "seq_member_member_id", sequenceName = "seq_member_member_id", 
		allocationSize = 1)
public class Member extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member_member_id")
	@Column(name = "member_id")
	private Integer memberId;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email_address")
	private String emailAddress;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "failed_logins")
	private Short failedLogins;
	@Column(name = "is_active")
	private Boolean isActive;
	@Column(name = "registered_at")
	private Date registeredAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	private Employee updatedBy;
}