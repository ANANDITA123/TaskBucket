package com.learn.taskbucket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
@EnableAutoConfiguration*/
@SpringBootApplication
public class TaskbucketApplication extends SpringBootServletInitializer {

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder builder) { return
	 * builder.sources(TaskbucketApplication.class); }
	 */

	public static void main(String[] args) {
		SpringApplication.run(TaskbucketApplication.class, args);
	}

}
