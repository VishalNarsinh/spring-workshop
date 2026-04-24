package com.example.demo.service.payment.provider;

import com.example.demo.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("paypal")
@Slf4j
public class PaypalPaymentProvider implements PaymentProvider {

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
