package com.example.demo.di;

import org.springframework.stereotype.Component;

@Component
public class ConstructorInjectionDemo {

	private final GreetingService greetingService;

	public ConstructorInjectionDemo(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	public String run() {
		return greetingService.message("Constructor Injection");
	}
}
