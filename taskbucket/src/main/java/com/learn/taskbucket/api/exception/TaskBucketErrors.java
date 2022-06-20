package com.learn.taskbucket.api.exception;

public enum TaskBucketErrors {
	VALIDATION_ERROR("VE-001", "Validation error"),
	INCORRECT_INPUT("VE-002", "Please check the input you have provided"),
	INTERNAL_SERVER_ERROR("EC-000", "Unhandled exception occoured"),
	MISSING_REQUEST_BODY("EC-001", "request body not present"),
	MISSING_TASK_ID("EC-002", "Missing mandatory field TaskId"),
	MISSING_UPDATE_BY("EC-003", "Missing mandatory field updated_by"),
	TASK_ID_DOESNOT_EXIST("EC-004", "Task Id Does not exist"),
	AUTHARIZATION_EXCEPTION("EC-005", "you are not allowed to perform this activity, kindly check with admin"),
	NO_DATA_FOUND("EC-006", "No Record Available"),
	INCONSISTANT_RANGE("EC-007", "Please Provide a valid range"),

	INVALID_USER_NAME("EC-101", "User name is invalid"),
	INVALID_EMAIL("EC-102", "email id is invalid"),
	INVALID_ROLE("EC-103", "Provided role is not present"),
	INVALID_USER_STATE("EC-104", "User state is invalid"),
	INVALID_USER_ID("EC-105", "User Id is invalid"),
	INCORRECT_ROLE("EC-106", "Incorrect user-role mapping"),
	UNABLE_TO_DELETE("EC-107", "Unable to delete, please check the request details"),
	INVALID_START("EC-108", "Provided start index is out of reach"),
	INVALID_END("EC-109", "Provided end index is out of reach"),
	INVALID_VIEW_TYPE("FC-109", "Please provide supported view types i.e.(OPEN, CLOSED, INPROGRESS, ALL)"),
	INVALID_RANGE("EC-109", "The range of the data set is invalid"),
	EMPTY_USER_NAME("EC-201", "User name cannot be null or blank"),
	EMPTY_EMAIL("EC-202", "Email id cannot be null or blank"),	
	EMPTY_ROLE("EC-203", "Role cannot be null or blank"),
	EMPTY_HEADLINE("EC-204", "Headline cannot be null or blank"),
	EMPTY_CREATOR_ID("EC-205", "Please provide a valid creator id"),
	EMPTY_UPDATE_FIELD("EC-206", "No data for update task"),

	EMAIL_ALREADY_PRESENT("EC-301", "user already exists for this email"),
	EMAIL_NOT_PRESENT("EC-302", "Provided email id does not exists"),
	INVALID_DESCRIPTION_LENGTH("EC-303", "Provided description is excceding the max length limit");
	
	   private final String errorCode;
	   private final String errorMessage;
	   TaskBucketErrors(final String errorCode, final String errorMessage) {
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
		    }
	   

	    public String getErrorCode() {
		return errorCode;
	    }

	    public String getErrorMessage() {
		return errorMessage;
	    }

	   
		
		 
	
}
