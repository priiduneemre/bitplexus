package com.neemre.bitplexus.backend.model;

import java.util.Date;

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
public class Member extends Entity {
	
	private Integer memberId;
	private String username;
	private String password;
	private String emailAddress;
	private String phoneNumber;
	private Short failedLogins;
	private Boolean isActive;
	private Date registeredAt;
	private Date updatedAt;
	private Employee updatedBy;
}