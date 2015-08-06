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
@Dto("com.neemre.bitplexus.backend.model.Visit")
public class VisitDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DtoField(value = "visitId", readOnly = true)
	private Long visitId;
	@DtoField(value = "member.username", readOnly = true, entityBeanKeys = {"Member"})
	private String username;
	@DtoField("ipAddress")
	private String ipAddress;
	@DtoField(value = "loginAt", readOnly = true)
	private Date loginAt;
}