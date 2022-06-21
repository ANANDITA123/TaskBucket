package com.learn.taskbucket.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.taskbucket.api.dto.AddUserRequest;
import com.learn.taskbucket.api.dto.AddUserResponse;
import com.learn.taskbucket.api.dto.UpdateUserRequest;
import com.learn.taskbucket.api.dto.UpdateUserResponse;
import com.learn.taskbucket.api.service.SaveUserService;
import com.learn.taskbucket.api.service.UpdateUserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	SaveUserService userServiceImp;
	@Autowired
	UpdateUserService updateUserService;
	
	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AddUserResponse> addUser(@RequestBody final AddUserRequest addUserRequest) {
		return new ResponseEntity<>(userServiceImp.saveUser(addUserRequest), HttpStatus.CREATED);
	}


	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/update/{userId}")
	public @ResponseBody ResponseEntity<UpdateUserResponse> updateUser(
			@Valid @RequestBody final UpdateUserRequest request, @PathVariable(required = true) int userId) {
		return new ResponseEntity<>(updateUserService.modifyUser(request, userId), HttpStatus.OK);
	}

}
