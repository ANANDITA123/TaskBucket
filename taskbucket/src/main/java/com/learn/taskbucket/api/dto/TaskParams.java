package com.learn.taskbucket.api.dto;

import java.time.LocalDateTime;

import com.learn.taskbucket.api.constant.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskParams {

	private String headline;
	private String description;
	private int assignee_id;
	private LocalDateTime eod;
	private TaskStatus task_status;
}
