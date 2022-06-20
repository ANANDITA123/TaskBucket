package com.learn.taskbucket.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learn.taskbucket.api.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
		
}
