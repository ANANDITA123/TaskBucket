package com.learn.taskbucket.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learn.taskbucket.api.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findById(int ids);
	//public Optional<User> findByIdOptional(int ids);
	
	@Query(value = "select u from User u where email_id =:c")
	public Optional<User> findbyEmail(@Param(value = "c") String email_id);
	

}
