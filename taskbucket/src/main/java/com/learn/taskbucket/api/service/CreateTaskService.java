package com.learn.taskbucket.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.constant.TaskStatus;
import com.learn.taskbucket.api.dto.CreateTaskRequest;
import com.learn.taskbucket.api.dto.CreateTaskResponse;
import com.learn.taskbucket.api.entity.Task;
import com.learn.taskbucket.api.entity.User;
import com.learn.taskbucket.api.exception.TaskBucketErrors;
import com.learn.taskbucket.api.exception.TaskBucketException;
import com.learn.taskbucket.api.repository.TaskRepository;
import com.learn.taskbucket.api.repository.UserRepository;

@Service
public class CreateTaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

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
		
		if(null != request.getEod()) {
			LocalDateTime now = LocalDateTime.now();
			if(now.isAfter(request.getEod())) {
				throw new TaskBucketException(TaskBucketErrors.EOD_INVALID);
			}	

		}

		User user = userRepository.findById(request.getCreator_id());
		if(null == user) {
			throw new TaskBucketException(TaskBucketErrors.INVALID_CREATOR);
		}
		if(0 != request.getAssignee()) {
			user = userRepository.findById(request.getAssignee());
			if(null == user) {
				throw new TaskBucketException(TaskBucketErrors.INVALID_ASSIGNEE);
			}
		}
	}

}
