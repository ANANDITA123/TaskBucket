package com.learn.taskbucket.api.dto;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
	private String headline;
	private String description;
	private int creator_id;
	private int assignee;
	private LocalDateTime eod;
	
}
