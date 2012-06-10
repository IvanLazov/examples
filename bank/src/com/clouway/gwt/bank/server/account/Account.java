package com.clouway.gwt.bank.server.account;

import com.clouway.gwt.bank.shared.exceptions.ExceededDepositException;
import com.clouway.gwt.bank.shared.exceptions.InsufficientFundsException;
import com.clouway.gwt.bank.shared.exceptions.InvalidDepositException;
import com.clouway.gwt.bank.shared.exceptions.InvalidWithdrawException;

import java.io.Serializable;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class Account implements Serializable {

  private long accountId;
  private long userId;
  private double balance;

  public Account(double amount) {
    this.balance = amount;
  }

  public Account(long accountId, long userId, double amount) {
    this.accountId = accountId;
    this.userId = userId;
    this.balance = amount;
  }

  public void deposit(double amount) {

    if (amount > 10000.0) {
      throw new ExceededDepositException();
    }

    if (amount <= 0) {
      throw new InvalidDepositException();
    }

    this.balance += amount;
  }

  public double getBalance() {
    return balance;
  }

  public long getUserId() {
    return userId;
  }

  public void withdraw(double amount) {

    if (amount > balance) {
      throw new InsufficientFundsException();
    }

    if (amount <= 0) {
      throw new InvalidWithdrawException();
    }

    this.balance -= amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Account account = (Account) o;

    if (accountId != account.accountId) return false;
    if (Double.compare(account.balance, balance) != 0) return false;
    if (userId != account.userId) return false;

    return true;
  }
}
