package com.learn.taskbucket.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.learn.taskbucket.api.constant.RoleName;
import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.dto.DeleteTaskRequest;
import com.learn.taskbucket.api.dto.DeleteTaskResponse;
import com.learn.taskbucket.api.entity.Role;
import com.learn.taskbucket.api.entity.Task;
import com.learn.taskbucket.api.exception.TaskBucketErrors;
import com.learn.taskbucket.api.exception.TaskBucketException;
import com.learn.taskbucket.api.repository.TaskRepository;

@Service
public class RemoveTaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	RoleFinder finder;

	public DeleteTaskResponse deleteTask(DeleteTaskRequest req, int user_id) {
		int roleId = finder.getRoleId(user_id);
		Role role = finder.getTheRoleUserMapping(roleId);

		if (roleId == 0)
			throw new TaskBucketException(TaskBucketErrors.INCORRECT_ROLE);

		if (null == role)
			throw new TaskBucketException(TaskBucketErrors.INCORRECT_ROLE);

		Optional<Task> task = taskRepository.findById(req.getTask_id());
		if (task.isEmpty())
			throw new TaskBucketException(TaskBucketErrors.TASK_ID_DOESNOT_EXIST);

		if (!role.getName().equals(RoleName.ADMIN.getRole())) {
			throw new TaskBucketException(TaskBucketErrors.AUTHARIZATION_EXCEPTION);
		}

		return delete(req);

	}


	public DeleteTaskResponse delete(DeleteTaskRequest req) {
		taskRepository.deleteById(req.getTask_id());
		return DeleteTaskResponse.builder().status(Status.DELETED).task_id(req.getTask_id()).build();

	}

}
