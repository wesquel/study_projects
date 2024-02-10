package com.dev_addson.cloud_spring_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloudSpringServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudSpringServerApplication.class, args);
	}
	

}
