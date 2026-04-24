package com.example.demo.config;

import com.example.demo.service.payment.policy.PaymentPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class AppConfig {

	@Bean
	public PaymentPolicy paymentPolicy() {
		return new PaymentPolicy(Set.of("INR", "USD", "EUR"), 1_000_000.00);
	}
}
