package com.fourglabs.vaccinecallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fourglabs.vaccinecallback.dto.RegistrationForm;
import com.fourglabs.vaccinecallback.scheduler.VaccineCallbackJob;

@RestController
public class RESTAPIController {
	public static String latestTime;

	@GetMapping("/")
	public String hello() {
		return "hello balaji " + latestTime;
	}

	@GetMapping("/trigger/{districtId}")
	public String trigger(@PathVariable int districtId) {
		VaccineCallbackJob cs = new VaccineCallbackJob();

		cs.execute(districtId);
		
		return "success";
	}

	@PostMapping("/")
	public String submitNewRegistration(@RequestBody RegistrationForm rform) {
		return null;

	}
}
