package com.example.demo.exception;

public class PaymentProviderNotSupportedException extends RuntimeException {

	public PaymentProviderNotSupportedException(String providerCode) {
		super("Unsupported provider: " + providerCode);
	}
}
