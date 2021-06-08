package com.example.bankaccount.service;

import com.example.bankaccount.exception.AccountNotFoundException;
import com.example.bankaccount.model.Account;

public interface AccountService {

	/**
	 * Allow to get account from the bank account number
	 * @param accountNumber the count account number
	 * @return the account corresponding
	 * @throws AccountNotFoundException if no account number found with the specified account number
	 */
	Account getAccount (long accountNumber) throws AccountNotFoundException;
}
