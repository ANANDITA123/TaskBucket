package com.learn.taskbucket.api.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.learn.taskbucket.api.constant.TaskStatus;
import com.learn.taskbucket.common.DateTimeConvertor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_task")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Builder
public class UpdateTask {
	@Id
	private int task_id;
	private String headline;
	private String description;
	private String status;
	private int assignee;
	private Timestamp eod;
	private int updated_by;
	
	public void setEod(LocalDateTime timeStamp) {
		eod = DateTimeConvertor.getTimeStamp(timeStamp);
	}

	public Timestamp getEod() {
		return eod;
	}
	
	public void setStatus(TaskStatus taskStatus) {
		status = taskStatus.name();
	}

	public String getStatus() {
		return status;
	}

}
