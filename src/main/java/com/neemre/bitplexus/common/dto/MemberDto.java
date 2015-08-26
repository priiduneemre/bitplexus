package com.neemre.bitplexus.common.dto;

import java.io.Serializable;
import java.util.Date;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Dto("com.neemre.bitplexus.backend.model.Member")
public class MemberDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DtoField(value = "memberId", readOnly = true)
	private Integer memberId;
	@DtoField(value = "username", readOnly = true)
	private String username;
	@DtoField(value = "password", readOnly = true)
	private String password;
	@DtoField("emailAddress")
	private String emailAddress;
	@DtoField("phoneNumber")
	private String phoneNumber;
	@DtoField(value = "failedLogins", readOnly = true)
	private Short failedLogins;
	@DtoField(value = "isActive", readOnly = true)
	private Boolean isActive;
	@DtoField(value = "registeredAt", readOnly = true)
	private Date registeredAt;
	@DtoField(value = "updatedAt", readOnly = true)
	private Date updatedAt;
	@DtoField(value = "updatedBy.employeeId", readOnly = true, entityBeanKeys = {"Employee"})
	private Integer updatedById;
}