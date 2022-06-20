package com.learn.taskbucket.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learn.taskbucket.api.entity.Task;
import com.learn.taskbucket.api.entity.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

	@Query(value = "DELETE FROM Task WHERE task_id=:tId")
	public Optional<Task> deleteByTaskId(@Param(value = "tId") int task_id);
	
	
	@Query(value = "SELECT t FROM Task t where status =:s")
	public List<Task> findByStatus(@Param("s") String status);
	
	
	

}
