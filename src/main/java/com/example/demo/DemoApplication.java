package com.example.demo;

import com.example.demo.di.ConstructorInjectionDemo;
import com.example.demo.di.FieldInjectionDemo;
import com.example.demo.di.SetterInjectionDemo;
import com.example.demo.entity.Payment;
import com.example.demo.service.payment.provider.PaymentProvider;
import com.example.demo.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			PaymentService paymentService,
			PaymentProvider defaultPaymentProvider,
			@Qualifier("paypal") PaymentProvider paypalPaymentProvider,
			ConstructorInjectionDemo constructorInjectionDemo,
			SetterInjectionDemo setterInjectionDemo,
			FieldInjectionDemo fieldInjectionDemo
	) {
		return (String... args) -> {

			log.info("{}", constructorInjectionDemo.run());
			log.info("{}", setterInjectionDemo.run());
			log.info("{}", fieldInjectionDemo.run());

			log.info("Default provider selected by @Primary: {}", defaultPaymentProvider.providerCode());
			log.info("Provider selected by @Qualifier(\"paypal\"): {}", paypalPaymentProvider.providerCode());

			Payment payment = new Payment("TX_411212", 4545.85, "INR");
			paymentService.executePayment(payment);
			log.info("Sample payment execution completed");
		};
	}

}
