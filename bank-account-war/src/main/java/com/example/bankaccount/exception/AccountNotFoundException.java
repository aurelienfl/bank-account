package com.example.bankaccount.exception;

public class AccountNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8885782704625050254L;

	public AccountNotFoundException (String accountNumber) {
		super(String.format("The account number %s does not refer to an existing account in the bank.", accountNumber));
	}
}
 