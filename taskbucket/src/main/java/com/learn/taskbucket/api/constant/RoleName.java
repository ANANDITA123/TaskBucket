package com.learn.taskbucket.api.constant;

import java.util.Arrays;

import lombok.Getter;

public enum RoleName {

	USER("user"), ADMIN("admin"), EX_USER("ex user"), INVALID_ROLE("role is missing");

	@Getter
	private String role;

	RoleName(String role) {
		this.role = role;
	}

	public static RoleName forMessage(final String errorMsg) {
		return Arrays.stream(values()).filter(Role -> Role.getRole().equals(errorMsg)).findFirst().orElse(INVALID_ROLE);
	}

}
