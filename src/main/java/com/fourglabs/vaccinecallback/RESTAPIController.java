package com.fourglabs.vaccinecallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fourglabs.vaccinecallback.dto.RegistrationForm;

@RestController
public class RESTAPIController {
  @GetMapping("/")
  public String hello() {
    return "hello balaji garu!";
  }
  
  @PostMapping("/")
  public String submitNewRegistration(@RequestBody RegistrationForm rform) {
	return null;
	  
  }
}
