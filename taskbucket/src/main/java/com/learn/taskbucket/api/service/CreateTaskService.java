package com.learn.taskbucket.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.constant.TaskStatus;
import com.learn.taskbucket.api.dto.CreateTaskRequest;
import com.learn.taskbucket.api.dto.CreateTaskResponse;
import com.learn.taskbucket.api.entity.Task;
import com.learn.taskbucket.api.exception.TaskBucketErrors;
import com.learn.taskbucket.api.exception.TaskBucketException;
import com.learn.taskbucket.api.repository.TaskRepository;

@Service
public class CreateTaskService {

	@Autowired
	TaskRepository taskRepository;


	public CreateTaskResponse createTask(CreateTaskRequest request) {
		Task task;
		validateCreateTask(request);

		task = Task.build(0, request.getHeadline(), request.getDescription(), TaskStatus.OPEN.name(),
				request.getCreator_id(), request.getAssignee(), null, request.getCreator_id());
		try {
			taskRepository.save(task);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new TaskBucketException(TaskBucketErrors.INTERNAL_SERVER_ERROR);
		}
		return CreateTaskResponse.builder().status(Status.SUCCESS).task_id(task.getTask_id())
				.task_status(task.getStatus()).build();

	}

	
	

	public void validateCreateTask(CreateTaskRequest request) {
		if (null == request.getHeadline() || request.getHeadline().isEmpty()) {
			throw new TaskBucketException(TaskBucketErrors.EMPTY_HEADLINE);
		}
		if (0 == request.getCreator_id()) {
			throw new TaskBucketException(TaskBucketErrors.EMPTY_CREATOR_ID);
		}

	}

}
