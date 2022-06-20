package com.learn.taskbucket.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConvertor {

	public static Timestamp getTimeStamp(LocalDateTime ldt) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Timestamp ts = Timestamp.valueOf(ldt.toString());
		return ts;
	}

}
