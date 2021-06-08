package com.example.bankaccount.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankaccount.exception.AccountNotFoundException;
import com.example.bankaccount.exception.InsufficientBalanceException;
import com.example.bankaccount.model.Account;
import com.example.bankaccount.model.Operation;
import com.example.bankaccount.service.AccountService;
import com.example.bankaccount.service.OperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OperationController {

	private static final Log LOGGER = LogFactory.getLog(OperationController.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private OperationService operationService;
	
	@GetMapping(path = "/api/makeDeposit", produces = "application/json")
	public Account makeDepositOperation (BigDecimal amount, Long accountNumber) {
		Account account = null;
		try {
			account = accountService.getAccount(accountNumber);
		} catch (AccountNotFoundException e) {
			LOGGER.error("Account not found", e);
		}
		operationService.makeDeposit(amount, account);
		try {
			return accountService.getAccount(accountNumber);
		} catch (AccountNotFoundException e) {
			LOGGER.error("Account not found", e);
			return null;
		}
	}
	
	@GetMapping(path = "/api/makeWithdrawal", produces = "application/json")
	public Account makeWithdrawalOperation (BigDecimal amount, Long accountNumber) {
		Account account = null;
		try {
			account = accountService.getAccount(accountNumber);
		} catch (AccountNotFoundException e) {
			LOGGER.error("Account not found", e);
		}
		try {
			operationService.makeWithdrawal(amount, account);
		} catch (InsufficientBalanceException e) {
			LOGGER.error("Insufficient balance to make this withdrawal", e);
		}
		try {
			return accountService.getAccount(accountNumber);
		} catch (AccountNotFoundException e) {
			LOGGER.error("Account not found", e);
			return null;
		}
	}
	
	@GetMapping(path = "/api/searchOperations", produces = "application/json")
	public String searchOperations (Long accountNumber) throws JsonProcessingException {
		ObjectMapper obj = new ObjectMapper();
		Account account = null;
		try {
			account = accountService.getAccount(accountNumber);
		} catch (AccountNotFoundException e) {
			LOGGER.error("Account not found", e);
			return obj.writeValueAsString(new ArrayList<>());
		}
		return obj.writeValueAsString(operationService.searchOperationsForAccount(account));
	}
}
