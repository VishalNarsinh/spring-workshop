package com.example.demo.service.payment.provider;

import com.example.demo.entity.Payment;

public interface PaymentProvider {

	String providerCode();

	void process(Payment payment);
}
