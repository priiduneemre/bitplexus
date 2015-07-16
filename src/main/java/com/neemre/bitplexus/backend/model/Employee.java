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
public class Employee extends Person {

	private Integer employeeId;
	private Date bornOn;
	private String iban;
	private Date employedOn;
	private Date resignedOn;
	private Boolean isActive;
	private Date createdAt;
}