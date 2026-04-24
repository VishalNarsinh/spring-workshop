package com.example.demo;

import com.example.demo.di.ConstructorInjectionDemo;
import com.example.demo.di.FieldInjectionDemo;
import com.example.demo.di.SetterInjectionDemo;
import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;
import com.example.demo.service.payment.provider.PaymentProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PaymentService paymentService, PaymentProvider defaultPaymentProvider,
			@Qualifier("paypal") PaymentProvider paypalPaymentProvider, ConstructorInjectionDemo constructorInjectionDemo,
			SetterInjectionDemo setterInjectionDemo, FieldInjectionDemo fieldInjectionDemo) {
		return (String... args) -> {

			log.info("{}", constructorInjectionDemo.run());
			log.info("{}", setterInjectionDemo.run());
			log.info("{}", fieldInjectionDemo.run());

			log.info("Default provider selected by @Primary: {}", defaultPaymentProvider.providerCode());
			log.info("Provider selected by @Qualifier(\"paypal\"): {}", paypalPaymentProvider.providerCode());

			Payment payment = new Payment("TX_411212", 4545.85, "INR");
			paymentService.executePayment(payment);
			log.info("payment execution with default provider completed");

			Payment paypalPayment = new Payment("TX_134321", 500, "USD", "paypal");
			paymentService.executePayment(paypalPayment);
			log.info("payment execution with paypal provider completed");
		};
	}

}
