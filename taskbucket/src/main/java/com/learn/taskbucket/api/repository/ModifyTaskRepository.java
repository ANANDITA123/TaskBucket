package com.learn.taskbucket.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.taskbucket.api.entity.Task;
import com.learn.taskbucket.api.entity.UpdateTask;

@Repository
public interface ModifyTaskRepository extends JpaRepository<UpdateTask, Integer>{

	
}
