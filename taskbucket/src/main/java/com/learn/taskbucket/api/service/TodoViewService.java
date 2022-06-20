package com.learn.taskbucket.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.constant.TaskStatus;
import com.learn.taskbucket.api.dto.TodoViewRequest;
import com.learn.taskbucket.api.dto.TodoViewResponse;
import com.learn.taskbucket.api.entity.Task;
import com.learn.taskbucket.api.exception.TaskBucketErrors;
import com.learn.taskbucket.api.exception.TaskBucketException;
import com.learn.taskbucket.api.repository.TaskRepository;

@Service
public class TodoViewService {

	@Autowired
	TaskRepository taskRepository;

	public TodoViewResponse findTodos(TodoViewRequest request) {
		TodoViewResponse response;
		List<Task> taskList = null;

		if (request.getView_type().equals(TaskStatus.ALL)) {
			taskList = taskRepository.findAll();

		} else if (request.getView_type().equals(TaskStatus.OPEN) || request.getView_type().equals(TaskStatus.CLOSED)
				|| request.getView_type().equals(TaskStatus.INPROGRESS)) {
			taskList = taskRepository.findByStatus(request.getView_type().name());
		} else {
			throw new TaskBucketException(TaskBucketErrors.INVALID_VIEW_TYPE);

		}

		try {
			response = TodoViewResponse.builder().status(Status.SUCCESS)
					.viewData(taskList.subList(request.getStart() - 1, request.getEnd() - 1)).build();
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TaskBucketException(TaskBucketErrors.INCONSISTANT_RANGE);
		}
		return response;
	}

	public void validationChecks(TodoViewRequest request) {
		if (request.getStart() <= 0 || request.getStart() >= request.getEnd())
			throw new TaskBucketException(TaskBucketErrors.INVALID_START);
		if (request.getEnd() <= 0)
			throw new TaskBucketException(TaskBucketErrors.INVALID_END);

	}

}
