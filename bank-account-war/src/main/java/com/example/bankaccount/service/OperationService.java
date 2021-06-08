package com.example.bankaccount.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.bankaccount.exception.InsufficientBalanceException;
import com.example.bankaccount.model.Account;
import com.example.bankaccount.model.Operation;

public interface OperationService {

	/**
	 * Allow to make deposit on a bank account
	 * @param amount the amount of the deposit
	 * @param accountNumber the account number where make the deposit
	 */
	void makeDeposit(BigDecimal amount, Account account);
	
	/**
	 * Allow to make withdrawal on a bank account
	 * @param amount the amount of the withdrawal
	 * @param accountNumber the account number where make the withdrawal
	 * @throws InsufficientBalanceException if the account balance is insufficient to make the withdrawal
	 */
	void makeWithdrawal(BigDecimal amount, Account account) throws InsufficientBalanceException;
	
	/**
	 * Get all operations carried on a bank account
	 * @param accountNumber the account number where get all its operations
	 * @return All the operations on this bank account
	 */
	List<Operation> searchOperationsForAccount(Account account);
}
