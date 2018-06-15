package com.example.usergroup;

import org.camunda.bpm.extension.reactor.spring.EnableCamundaEventBus;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableProcessApplication
@SpringBootApplication
@EnableCamundaEventBus
public class UserGroupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserGroupServiceApplication.class, args);
	}
}
