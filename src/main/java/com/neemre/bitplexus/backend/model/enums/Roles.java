package com.neemre.bitplexus.backend.model.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Roles {

	WEBSITE_EDITOR,
	SUPPORT_ENGINEER,
	DATABASE_MANAGER,
	DATABASE_ADMIN;


	public String getName() {
		return name();
	}
}