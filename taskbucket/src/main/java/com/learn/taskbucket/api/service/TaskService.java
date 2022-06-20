package com.learn.taskbucket.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.taskbucket.api.constant.RoleName;
import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.constant.TaskStatus;
import com.learn.taskbucket.api.dto.CreateTaskRequest;
import com.learn.taskbucket.api.dto.CreateTaskResponse;
import com.learn.taskbucket.api.dto.DeleteTaskRequest;
import com.learn.taskbucket.api.dto.DeleteTaskResponse;
import com.learn.taskbucket.api.dto.TaskParams;
import com.learn.taskbucket.api.dto.UpdateTaskRequest;
import com.learn.taskbucket.api.dto.UpdateTaskResponse;
import com.learn.taskbucket.api.entity.Role;
import com.learn.taskbucket.api.entity.Task;
import com.learn.taskbucket.api.entity.UpdateTask;
import com.learn.taskbucket.api.entity.User;
import com.learn.taskbucket.api.exception.TaskBucketErrors;
import com.learn.taskbucket.api.exception.TaskBucketException;
import com.learn.taskbucket.api.repository.ModifyTaskRepository;
import com.learn.taskbucket.api.repository.RoleRepository;
import com.learn.taskbucket.api.repository.TaskRepository;
import com.learn.taskbucket.api.repository.UserRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;
	@Autowired
	ModifyTaskRepository modifyTaskRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	public CreateTaskResponse createTask(CreateTaskRequest request) {
		Task task;
		validateCreateTask(request);

		task = Task.build(0, request.getHeadline(), request.getDescription(), TaskStatus.OPEN.name(),
				request.getCreator_id(), request.getAssignee(), null, request.getCreator_id());
		try {
			taskRepository.save(task);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new TaskBucketException(TaskBucketErrors.INTERNAL_SERVER_ERROR);
		}
		return CreateTaskResponse.builder().status(Status.SUCCESS).task_id(task.getTask_id())
				.task_status(task.getStatus()).build();

	}

	public UpdateTaskResponse updateTask(UpdateTaskRequest request, int userId, int task_id) {
		validateUpdateTask(request, userId, task_id);
		UpdateTask uTask = new UpdateTask();
		if (0 != request.getTaskParams().getAssignee_id()) {
			uTask.setAssignee(request.getTaskParams().getAssignee_id());
		}
		if (null != request.getTaskParams().getDescription()) {
			uTask.setDescription(request.getTaskParams().getDescription());
		}

		if (null != request.getTaskParams().getEod()) {
			uTask.setEod(request.getTaskParams().getEod());
		}

		if (null != request.getTaskParams().getHeadline()) {
			uTask.setHeadline(request.getTaskParams().getHeadline());
		}

		if (null != request.getTaskParams().getTask_status()) {
			uTask.setStatus(request.getTaskParams().getTask_status());

		}

		uTask.setTask_id(task_id);
		uTask.setUpdated_by(userId);
		try {
			modifyTaskRepository.save(uTask);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new TaskBucketException(TaskBucketErrors.INTERNAL_SERVER_ERROR);
		}
		return UpdateTaskResponse.builder().status(Status.SUCCESS).task_id(uTask.getTask_id()).build();

	}

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

	public void validateUpdateTask(UpdateTaskRequest request, int updated_by, int task_id) {
		if (0 == task_id) {
			throw new TaskBucketException(TaskBucketErrors.MISSING_TASK_ID);
		}
		if (0 == updated_by) {
			throw new TaskBucketException(TaskBucketErrors.MISSING_UPDATE_BY);
		}
		TaskParams params = request.getTaskParams();
		if (null == params) {
			throw new TaskBucketException(TaskBucketErrors.EMPTY_UPDATE_FIELD);
		}
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
