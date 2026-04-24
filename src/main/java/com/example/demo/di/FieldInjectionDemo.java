package com.example.demo.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldInjectionDemo {

	@Autowired
	private GreetingService greetingService;

	public String run() {
		return greetingService.message("Field Injection");
	}
}
