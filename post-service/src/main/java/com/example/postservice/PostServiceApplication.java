package com.example.postservice;

import com.example.postservice.config.DataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PostServiceApplication {


	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(PostServiceApplication.class, args);
		DataInitializer dataInitializer = app.getBean(DataInitializer.class);

		dataInitializer.init();
	}

}

