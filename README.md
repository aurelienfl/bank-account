# Bank account

## Installation

This project was build with Maven on Java 8, to generate an EAR file (bank-account/bank-account-ear/target/bank-account.ear)
This EAR file must be deployed on Weblogic 12c R2

Sql scripts located in /bank-account/sql must be run on Oracle Database 18C > in this order :

- /sql/create.sql
- /sql/insert.sql

## User guide

This application provides an API with following services, than can be use in a web browser

### Service to make a deposit in an account

http://{server-context}:{server-port}/bank-account/api/makeDeposit?amount=500&accountNumber=123456

This exemple allow to make a deposit of 500 on the account number 123456

### Service to make a withdrawal from an account

http://{server-context}:{server-port}/bank-account/api/makeWithdrawal?amount=600&accountNumber=123456

This exemple allow to make a withdrawal of 600 on the account number 123456

### Service to see the history of operations of an account

http://{server-context}:{server-port}/bank-account/api/searchOperations?accountNumber=123456

This exemple allow to see the operations on the account number 123456
