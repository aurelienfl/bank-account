package com.example.bankaccount.exception;

public class InsufficientBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2109821420486005801L;

	public InsufficientBalanceException (String accountNumber) {
		super(String.format("The account number %s does not have a sufficient balance to make the requested withdrawal.", accountNumber));
	}
}
