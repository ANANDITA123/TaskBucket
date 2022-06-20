package com.learn.taskbucket.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.taskbucket.api.constant.Status;
import com.learn.taskbucket.api.dto.AddUserRequest;
import com.learn.taskbucket.api.dto.AddUserResponse;
import com.learn.taskbucket.api.dto.UpdateUserRequest;
import com.learn.taskbucket.api.dto.UpdateUserResponse;
import com.learn.taskbucket.api.entity.Role;
import com.learn.taskbucket.api.entity.User;
import com.learn.taskbucket.api.exception.TaskBucketErrors;
import com.learn.taskbucket.api.exception.TaskBucketException;
import com.learn.taskbucket.api.repository.RoleRepository;
import com.learn.taskbucket.api.repository.UserRepository;

@Service
public class UserServiceImp {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	RoleRepository roleRepository;
	
	public AddUserResponse saveUser(AddUserRequest addUserRequest) {
		if(addUserRequest.getIs_active()<0 || addUserRequest.getIs_active()>1 )
			throw new TaskBucketException(TaskBucketErrors.INVALID_USER_STATE);

		validateRole(addUserRequest.getRole_id());	
		validateEmail(addUserRequest.getEmail_id());
		User user = User
				.build(0, addUserRequest.getRole_id(),
						addUserRequest.getUser_name(), 
						addUserRequest.getEmail_id(), 
						-1 == addUserRequest.getIs_active()?1:addUserRequest.getIs_active());
		try {
		userRepository.save(user);
		}catch (Exception  e) {
			throw new TaskBucketException(TaskBucketErrors.INTERNAL_SERVER_ERROR);
		}
		AddUserResponse response = AddUserResponse.builder().userId(user.getUser_id()).status(Status.SUCCESS).build();
		return response;
	}
	
	public UpdateUserResponse modifyUser(UpdateUserRequest updateUserRequest, int userId) {
		User user = userRepository.findById(userId);
	    AddUserRequest userdetails = updateUserRequest.getUserdata();
		
		if(null != userdetails.getEmail_id() && !userdetails.getEmail_id().isEmpty())
		{
			user.setEmail_id(userdetails.getEmail_id());
		}
		if(userdetails.getIs_active() != -1)
		{
			user.setIs_active(userdetails.getIs_active());
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
	
	public void validateRole(int role) {
		Optional<Role> rol;
		if(role ==0)
			throw new TaskBucketException(TaskBucketErrors.EMPTY_ROLE);
		try {
			 rol = roleRepository.findById(role);
		}catch (Exception e) {
			throw e;
		}
		if(rol.isEmpty()) 
			throw new TaskBucketException(TaskBucketErrors.INVALID_ROLE);

	}
	
	public void validateEmail(String email) {
		Optional<User> user;
		if(null == email || email.isEmpty() || email.isBlank())
			throw new TaskBucketException(TaskBucketErrors.EMPTY_EMAIL);
		try {
			user = (Optional<User>) userRepository.findbyEmail(email);
		}catch (Exception e) {
			throw e;
		}
		if(!user.isEmpty()) 
			throw new TaskBucketException(TaskBucketErrors.EMAIL_ALREADY_PRESENT);

	}
	
	
	/*
	 * public String getRole(RoleName roleName) { return
	 * String.valueOf(roleRepository.findRoleByName(roleName.name()).getRole_id());
	 * }
	 */
	

}
