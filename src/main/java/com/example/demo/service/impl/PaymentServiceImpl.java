package com.example.demo.service.impl;

import com.example.demo.entity.Payment;
import com.example.demo.exception.PaymentProviderNotSupportedException;
import com.example.demo.exception.PaymentValidationException;
import com.example.demo.service.PaymentService;
import com.example.demo.service.payment.policy.PaymentPolicy;
import com.example.demo.service.payment.provider.PaymentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
//@Slf4j
public class PaymentServiceImpl implements PaymentService {

	private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
	private final Map<String, PaymentProvider> providerMap;
	private final PaymentPolicy paymentPolicy;
	private final String defaultProviderCode;

	public PaymentServiceImpl(Map<String, PaymentProvider> providerMap, PaymentPolicy paymentPolicy,
			@Value("${app.payment.default-provider}") String defaultProviderCode) {
		this.providerMap = providerMap;
		this.paymentPolicy = paymentPolicy;
		this.defaultProviderCode = defaultProviderCode.toLowerCase();
	}

	@Override
	public boolean executePayment(Payment payment) {
		log.info("Routing payment to payment provider");
		try {
			validatePayment(payment);
			PaymentProvider paymentProvider = resolveProvider(payment);
			paymentProvider.process(payment);
			return true;
		} catch (Exception e) {
			log.error("Error occurred while processing payment with transaction id: {}", payment.getTransactionId(), e);
			return false;
		}
	}

	private PaymentProvider resolveProvider(Payment payment) {
		String requestedProvider = payment.getProvider();
		String providerCode = (requestedProvider == null || requestedProvider.isBlank()) ? defaultProviderCode : requestedProvider.toLowerCase();

		PaymentProvider provider = providerMap.get(providerCode);
		if (provider == null) {
			throw new PaymentProviderNotSupportedException(providerCode);
		}

		payment.setProvider(providerCode);
		return provider;
	}

	private void validatePayment(Payment payment) {
		String normalizedCurrency = payment.getCurrency() == null ? null : payment.getCurrency().toUpperCase();
		if (normalizedCurrency == null || !paymentPolicy.supportedCurrencies().contains(normalizedCurrency)) {
			throw new PaymentValidationException("Unsupported currency: " + payment.getCurrency());
		}
		if (payment.getAmount() <= 0 || payment.getAmount() > paymentPolicy.maxAmount()) {
			throw new PaymentValidationException("Amount must be between 0 and " + paymentPolicy.maxAmount());
		}
		payment.setCurrency(normalizedCurrency);
	}
}
