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
public class Person extends Member {
	
	private Integer personId;
	private String firstName;
	private String lastName;
	private Date createdAt;
	private Date updatedAt;
}