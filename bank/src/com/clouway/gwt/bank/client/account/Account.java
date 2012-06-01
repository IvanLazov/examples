package com.clouway.gwt.bank.client.account;

import com.clouway.gwt.bank.client.exceptions.DepositZeroAmountException;
import com.clouway.gwt.bank.client.exceptions.ExcessDepositAmountException;
import com.clouway.gwt.bank.client.exceptions.InsufficientFundsException;
import com.clouway.gwt.bank.client.exceptions.NegativeDepositAmountException;
import com.clouway.gwt.bank.client.exceptions.NegativeWithdrawAmountException;
import com.clouway.gwt.bank.client.exceptions.WithdrawZeroAmountException;

import java.io.Serializable;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class Account implements Serializable {

  private double balance;

  public Account(double amount) {
    this.balance = amount;
  }

  public void deposit(double amount) {

    if (amount > 10000.0) {
      throw new ExcessDepositAmountException();
    }

    if (amount < 0) {
      throw new NegativeDepositAmountException();
    }

    if (amount == 0) {
      throw new DepositZeroAmountException();
    }

    this.balance += amount;
  }

  public double getBalance() {
    return balance;
  }

  public void withdraw(double amount) throws InsufficientFundsException {

    if (amount > balance) {
      throw new InsufficientFundsException();
    }

    if (amount == 0) {
      throw new WithdrawZeroAmountException();
    }

    if (amount < 0) {
      throw new NegativeWithdrawAmountException();
    }
    this.balance -= amount;
  }
}
