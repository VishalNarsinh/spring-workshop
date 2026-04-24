package com.example.demo.service.payment.provider;

import com.example.demo.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("paypal")
public class PaypalPaymentProvider implements PaymentProvider {

	private static final Logger log = LoggerFactory.getLogger(PaypalPaymentProvider.class);

	@Override
	public String providerCode() {
		return "paypal";
	}

	@Override
	public void process(Payment payment) {
		log.info("Processing payment with PayPal");
		log.info("{}", payment);
	}
}
