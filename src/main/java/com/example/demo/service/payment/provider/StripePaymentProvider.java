package com.example.demo.service.payment.provider;

import com.example.demo.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("stripe")
@Primary
@Slf4j
public class StripePaymentProvider implements PaymentProvider {

	@Override
	public String providerCode() {
		return "stripe";
	}

	@Override
	public void process(Payment payment) {
		log.info("Processing payment with Stripe");
		log.info("{}", payment);
	}
}
