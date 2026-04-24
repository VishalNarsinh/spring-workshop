package com.example.demo.di;

import org.springframework.stereotype.Component;

@Component
public class DefaultGreetingService implements GreetingService {

	@Override
	public String message(String injectionType) {
		return "DI demo using " + injectionType;
	}
}
