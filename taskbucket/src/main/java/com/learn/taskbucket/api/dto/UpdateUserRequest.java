package com.learn.taskbucket.api.dto;

import com.learn.taskbucket.api.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
	private AddUserRequest userdata;
	private String updatedBy;
	
}
