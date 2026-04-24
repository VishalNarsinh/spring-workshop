package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Payment {

	private String transactionId;

	private double amount;

	private String currency;

	private String provider;

	public Payment(String transactionId, double amount, String currency) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.currency = currency;
		this.provider = "stripe";
	}

	public Payment(String transactionId, double amount, String currency, String provider) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.currency = currency;
		this.provider = provider;
	}
}
