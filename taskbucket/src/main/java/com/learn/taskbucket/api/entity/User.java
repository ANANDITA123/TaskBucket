package com.learn.taskbucket.api.entity;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class User {
	@Id
	@GeneratedValue
	private int user_id;
	private int role_id;
	private String user_name;
	private String email_id;
	private Byte is_active;
	
	

}
