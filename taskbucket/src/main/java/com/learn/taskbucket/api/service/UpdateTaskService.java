package com.learn.taskbucket.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.constant.TaskStatus;
import com.learn.taskbucket.api.dto.TaskParams;
import com.learn.taskbucket.api.dto.UpdateTaskRequest;
import com.learn.taskbucket.api.dto.UpdateTaskResponse;
import com.learn.taskbucket.api.entity.UpdateTask;
import com.learn.taskbucket.api.exception.TaskBucketErrors;
import com.learn.taskbucket.api.exception.TaskBucketException;
import com.learn.taskbucket.api.repository.ModifyTaskRepository;
import com.learn.taskbucket.api.repository.UserRepository;

@Service
public class UpdateTaskService {
	
	@Autowired
	ModifyTaskRepository modifyTaskRepository;
	@Autowired
	UserRepository userRepository;
	
	public UpdateTaskResponse updateTask(UpdateTaskRequest request, int userId, int task_id) {
		if(userId != request.getUpdated_by()) {
			throw new TaskBucketException(TaskBucketErrors.AUTHARIZATION_EXCEPTION);
		}
		validateUpdateTask(request, userId, task_id);
		UpdateTask uTask = createEntity(request, userId, task_id);
		
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
	
	private UpdateTask createEntity(UpdateTaskRequest request, int updated_by, int task_id) {
		UpdateTask uTask = new UpdateTask();
		
		if (0 != request.getTaskParams().getAssignee()) {
			if(!userRepository.existsById(request.getTaskParams().getAssignee()))
				throw new TaskBucketException(TaskBucketErrors.INVALID_ASSIGNEE);
			uTask.setAssignee(request.getTaskParams().getAssignee());
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
		return uTask;

	}
	
	private void validateUpdateTask(UpdateTaskRequest request, int updated_by, int task_id) {
		
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
		
		UpdateTask data = modifyTaskRepository.findById(task_id).orElse(null);
		if(data==null)
		throw new TaskBucketException(TaskBucketErrors.TASK_ID_DOESNOT_EXIST);
		if(data.getStatus().equals(TaskStatus.CLOSED.name()))
			throw new TaskBucketException(TaskBucketErrors.TASK_ALREADY_CLOSED);

		if(!userRepository.existsById(updated_by))
			throw new TaskBucketException(TaskBucketErrors.INVALID_USER_ID);

	}


}
