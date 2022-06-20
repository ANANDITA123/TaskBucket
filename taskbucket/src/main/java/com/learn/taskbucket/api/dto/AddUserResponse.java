package com.learn.taskbucket.api.dto;

import com.learn.taskbucket.api.constant.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserResponse {

	private Status status;
    private int userId;
}
