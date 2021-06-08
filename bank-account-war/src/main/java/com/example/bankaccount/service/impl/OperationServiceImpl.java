package com.example.bankaccount.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bankaccount.exception.InsufficientBalanceException;
import com.example.bankaccount.model.Account;
import com.example.bankaccount.model.Operation;
import com.example.bankaccount.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	public void makeDeposit(BigDecimal amount, Account account) {
		if (account == null || amount == null) {
			return;
		}
		account.setBalance(account.getBalance().add(amount));
		account.update();
		Operation depositOperation = Operation.builder()
				.accountId(account.getId()).type(Operation.DEPOSIT_TYPE)
				.amount(amount).build();
		depositOperation.create();
	}

	public void makeWithdrawal(BigDecimal amount, Account account) throws InsufficientBalanceException {
		if (account == null || amount == null) {
			return;
		}
		BigDecimal newBalance = account.getBalance().subtract(amount);
		if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
			throw new InsufficientBalanceException(String.valueOf(account.getAccountNumber()));
		} else {
			account.setBalance(newBalance);
			account.update();
			Operation withdrawalOperation = Operation.builder()
					.accountId(account.getId()).type(Operation.WITHDRAWAL_TYPE)
					.amount(amount).build();
			withdrawalOperation.create();
		}
	}

	public List<Operation> searchOperationsForAccount(Account account) {
		if (account == null) {
			return new ArrayList<>();
		}
		return Operation.findAllByAccountId(account.getId());
	}

}
