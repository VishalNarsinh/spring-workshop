package com.example.demo.di;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConstructorInjectionDemo {

	private final GreetingService greetingService;

	public String run() {
		return greetingService.message("Constructor Injection");
	}
}
