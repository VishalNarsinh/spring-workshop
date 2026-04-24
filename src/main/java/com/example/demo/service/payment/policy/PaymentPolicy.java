package com.example.demo.service.payment.policy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class PaymentPolicy {

	private final Set<String> supportedCurrencies;
	private final double maxAmount;

	public Set<String> supportedCurrencies() {
		return supportedCurrencies;
	}

	public double maxAmount() {
		return maxAmount;
	}
}
