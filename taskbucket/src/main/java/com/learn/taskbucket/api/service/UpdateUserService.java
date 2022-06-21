package com.learn.taskbucket.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.dto.AddUserRequest;
import com.learn.taskbucket.api.dto.UpdateUserRequest;
import com.learn.taskbucket.api.dto.UpdateUserResponse;
import com.learn.taskbucket.api.entity.User;
import com.learn.taskbucket.api.exception.TaskBucketErrors;
import com.learn.taskbucket.api.exception.TaskBucketException;
import com.learn.taskbucket.api.repository.UserRepository;

@Service
public class UpdateUserService {

	@Autowired
	UserRepository userRepository;
	
	public UpdateUserResponse modifyUser(UpdateUserRequest updateUserRequest, int userId) {
		User user = userRepository.findById(userId);
	    AddUserRequest userdetails = updateUserRequest.getUserdata();
		
		if(null != userdetails.getEmail_id() && !userdetails.getEmail_id().isEmpty())
		{
			user.setEmail_id(userdetails.getEmail_id());	
			if(userRepository.findbyEmail(updateUserRequest.getUserdata().getEmail_id()).isEmpty());	
			{
				throw new TaskBucketException(TaskBucketErrors.EMAIL_UPDATE_NOT_ALLOWED);
			}
		}
		if(userdetails.getIs_active() != -1)
		{
			user.setIs_active(userdetails.getIs_active());
		}else {
			user.setIs_active(Byte.valueOf("1"));
		}
		if(null != userdetails.getUser_name() && !userdetails.getUser_name().isEmpty()) {
			user.setUser_name(userdetails.getUser_name());
		}
		
		
		
		try{
			userRepository.save(user);
		}catch (Exception  e) {
			throw new TaskBucketException(TaskBucketErrors.INTERNAL_SERVER_ERROR);
		}
		
		UpdateUserResponse response = UpdateUserResponse.builder().user_id(user.getUser_id()).status(Status.SUCCESS).build();
		return response;
	}
	
}
