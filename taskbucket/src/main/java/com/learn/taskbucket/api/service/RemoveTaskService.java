package com.learn.taskbucket.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.taskbucket.api.constant.RoleName;
import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.dto.DeleteTaskRequest;
import com.learn.taskbucket.api.dto.DeleteTaskResponse;
import com.learn.taskbucket.api.entity.Role;
import com.learn.taskbucket.api.entity.Task;
import com.learn.taskbucket.api.entity.User;
import com.learn.taskbucket.api.exception.TaskBucketErrors;
import com.learn.taskbucket.api.exception.TaskBucketException;
import com.learn.taskbucket.api.repository.RoleRepository;
import com.learn.taskbucket.api.repository.TaskRepository;
import com.learn.taskbucket.api.repository.UserRepository;

@Service
public class RemoveTaskService {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	UserRepository userRepository;

	public DeleteTaskResponse deleteTask(DeleteTaskRequest req, int user_id) {
		int roleId = getRoleId(user_id);
		Role role = getTheRoleUserMapping(roleId);

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

	public Role getTheRoleUserMapping(int roleId) {
		List<Role> listRole = new ArrayList<Role>();
		Optional<Role> role;
		try {
			role = roleRepository.findById(roleId);
			if (role.isPresent()) {
				listRole.add(role.get());
			}
			return listRole.get(0);
		} catch (Exception e) {
			throw new TaskBucketException(TaskBucketErrors.INTERNAL_SERVER_ERROR);
		}
	}

	public int getRoleId(int userId) {
		try {
			User user = userRepository.findById(userId);
			return user.getRole_id();
		} catch (Exception e) {
			throw new TaskBucketException(TaskBucketErrors.INVALID_USER_ID);
		}

	}

	public DeleteTaskResponse delete(DeleteTaskRequest req) {
		taskRepository.deleteById(req.getTask_id());
		return DeleteTaskResponse.builder().status(Status.DELETED).task_id(req.getTask_id()).build();

	}

}
