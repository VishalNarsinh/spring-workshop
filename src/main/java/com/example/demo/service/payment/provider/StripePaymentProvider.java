package com.example.demo.service.payment.provider;

import com.example.demo.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("stripe")
@Primary
public class StripePaymentProvider implements PaymentProvider {

	private static final Logger log = LoggerFactory.getLogger(StripePaymentProvider.class);

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
