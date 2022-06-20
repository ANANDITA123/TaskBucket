package com.learn.taskbucket.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.entity.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoViewResponse {
	private Status status;
	private List<Task> viewData;
}
