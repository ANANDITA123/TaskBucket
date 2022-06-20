package com.learn.taskbucket.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {
	private TaskParams taskParams;
	private int updated_by;
}
