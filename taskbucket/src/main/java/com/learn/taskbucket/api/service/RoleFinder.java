package com.learn.taskbucket.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.taskbucket.api.entity.Role;
import com.learn.taskbucket.api.entity.User;
import com.learn.taskbucket.api.exception.TaskBucketErrors;
import com.learn.taskbucket.api.exception.TaskBucketException;
import com.learn.taskbucket.api.repository.RoleRepository;
import com.learn.taskbucket.api.repository.UserRepository;

@Service
public class RoleFinder {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
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

	
}
