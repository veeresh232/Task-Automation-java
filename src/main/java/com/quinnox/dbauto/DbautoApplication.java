package com.quinnox.dbauto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.quinnox.dbauto.schedular.Monitor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class DbautoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbautoApplication.class, args);
		
	}

}
