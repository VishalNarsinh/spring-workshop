package com.example.demo.service.payment.policy;

import java.util.Set;

public class PaymentPolicy {

	private final Set<String> supportedCurrencies;
	private final double maxAmount;

	public PaymentPolicy(Set<String> supportedCurrencies, double maxAmount) {
		this.supportedCurrencies = supportedCurrencies;
		this.maxAmount = maxAmount;
	}

	public Set<String> supportedCurrencies() {
		return supportedCurrencies;
	}

	public double maxAmount() {
		return maxAmount;
	}
}
