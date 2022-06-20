package com.learn.taskbucket.api.dto;

import com.learn.taskbucket.api.constant.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskResponse {

	private int task_id;
	private Status status;
}
