package com.learn.taskbucket.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.taskbucket.api.dto.CreateTaskRequest;
import com.learn.taskbucket.api.dto.TodoViewRequest;
import com.learn.taskbucket.api.service.TodoViewService;

@RestController
@RequestMapping("/api/v1/task")
public class TodoFinderController {

	@Autowired
	TodoViewService viewService;
	
	@GetMapping(path = "/view")
	public ResponseEntity<Object> viewAllTaskBy(@RequestBody final TodoViewRequest todoViewRequest) {
		return new ResponseEntity<>(viewService.findTodos(todoViewRequest), HttpStatus.OK);
}

}
