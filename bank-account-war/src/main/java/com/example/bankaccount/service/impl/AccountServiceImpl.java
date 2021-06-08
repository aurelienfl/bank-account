package com.example.bankaccount.service.impl;

import org.springframework.stereotype.Service;

import com.example.bankaccount.exception.AccountNotFoundException;
import com.example.bankaccount.model.Account;
import com.example.bankaccount.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	public Account getAccount(long accountNumber) throws AccountNotFoundException {
		Account account = Account.findAccount(accountNumber);
		if (account == null) {
			throw new AccountNotFoundException(String.valueOf(accountNumber));
		}
		return account;
	}

}
