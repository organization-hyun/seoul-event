package com.hyun.seoul_event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SeoulEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeoulEventApplication.class, args);
	}

}
