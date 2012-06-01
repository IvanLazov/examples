package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.account.Account;
import com.clouway.gwt.bank.client.exceptions.DepositZeroAmountException;
import com.clouway.gwt.bank.client.exceptions.ExcessDepositAmountException;
import com.clouway.gwt.bank.client.exceptions.InsufficientFundsException;
import com.clouway.gwt.bank.client.exceptions.NegativeDepositAmountException;
import com.clouway.gwt.bank.client.exceptions.NegativeWithdrawAmountException;
import com.clouway.gwt.bank.client.exceptions.WithdrawZeroAmountException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountTest {

  private Account account;
  private double balance = 500.0;

  @Before
  public void setUp() {
    account = new Account(balance);
  }

  @Test
  public void depositPositiveAmount() {
    account.deposit(100.0);
    assertEquals(600.0, account.getBalance());
  }

  @Test(expected = DepositZeroAmountException.class)
  public void depositZeroAmount() {
    account.deposit(0);
    assertEquals(balance, account.getBalance());
  }

  @Test(expected = ExcessDepositAmountException.class)
  public void depositExcessAmount() {
    account.deposit(11000.0);
    assertEquals(balance, account.getBalance());
  }

  @Test(expected = NegativeDepositAmountException.class)
  public void depositNegativeAmount() {
    account.deposit(-100.0);
    assertEquals(balance, account.getBalance());
  }

  @Test
  public void withdrawPositiveAmount() {
    account.withdraw(100.0);
    assertEquals(400.0, account.getBalance());
  }

  @Test(expected = InsufficientFundsException.class)
  public void cantWithdrawWhenBalanceIsInsufficient() {
    account.withdraw(1000.0);
    assertEquals(balance, account.getBalance());
  }

  @Test(expected = WithdrawZeroAmountException.class)
  public void withdrawZeroAmount() {
    account.withdraw(0);
    assertEquals(balance, account.getBalance());
  }

  @Test(expected = NegativeWithdrawAmountException.class)
  public void withdrawNegativeAmount() {
    account.withdraw(-100.0);
    assertEquals(balance, account.getBalance());
  }
}
