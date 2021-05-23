package com.fourglabs.vaccinecallback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VaccineCallback {

	public static void main(String[] args) {
		SpringApplication.run(VaccineCallback.class, args);
	}	

}
