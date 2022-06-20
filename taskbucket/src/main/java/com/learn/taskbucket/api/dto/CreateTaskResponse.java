package com.learn.taskbucket.api.dto;

import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.constant.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskResponse {

	private Status status;
	private long task_id;
	private String task_status;
}
