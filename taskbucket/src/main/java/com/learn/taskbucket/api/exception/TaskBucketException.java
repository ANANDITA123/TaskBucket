package com.learn.taskbucket.api.exception;



public class TaskBucketException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;

	public TaskBucketException(TaskBucketErrors error) {
		super();
		this.errorCode = error.getErrorCode();
		this.errorMessage = error.getErrorMessage();
	}
	
	public TaskBucketException() {
		
	}
	
	 public String getErrorCode() {
	        return errorCode;
	    }

	    public String getErrorMessage() {
	        return errorMessage;
	    }

}
