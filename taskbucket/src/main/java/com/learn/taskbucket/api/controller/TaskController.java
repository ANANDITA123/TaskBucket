package com.learn.taskbucket.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.taskbucket.api.dto.CreateTaskRequest;
import com.learn.taskbucket.api.dto.DeleteTaskRequest;
import com.learn.taskbucket.api.dto.UpdateTaskRequest;
import com.learn.taskbucket.api.service.CreateTaskService;
import com.learn.taskbucket.api.service.RemoveTaskService;
import com.learn.taskbucket.api.service.UpdateTaskService;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
	@Autowired
	CreateTaskService taskService;
	@Autowired
	UpdateTaskService updateTaskService;
	@Autowired
	RemoveTaskService removeTaskService;

	
	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createTask(@RequestBody final CreateTaskRequest createTaskRequest) {
			return new ResponseEntity<>(taskService.createTask(createTaskRequest), HttpStatus.CREATED);
	}

	@PutMapping(path = "/add/{task_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateTaskDetails(@RequestBody final UpdateTaskRequest request,
			@RequestHeader(name = "userId", required = true) int userId, @PathVariable(required = true) int task_id)
			 {
			return new ResponseEntity<>(updateTaskService.updateTask(request, userId, task_id), HttpStatus.OK);
	}
	
	@DeleteMapping(path =  "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> removeTaskDetails(@RequestBody final DeleteTaskRequest request, @RequestHeader(name = "userId", required = true) int userId){
		return new ResponseEntity<>(removeTaskService.deleteTask(request, userId), HttpStatus.OK);

	}

}
