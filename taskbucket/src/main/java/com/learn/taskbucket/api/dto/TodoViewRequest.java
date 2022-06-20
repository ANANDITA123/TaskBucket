package com.learn.taskbucket.api.dto;

import com.learn.taskbucket.api.constant.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoViewRequest {
	private TaskStatus view_type;
	private int start;
	private int end;

	

}
