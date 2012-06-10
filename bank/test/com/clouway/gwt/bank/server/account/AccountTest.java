package com.clouway.gwt.bank.server.account;

import com.clouway.gwt.bank.shared.exceptions.ExceededDepositException;
import com.clouway.gwt.bank.shared.exceptions.InsufficientFundsException;
import com.clouway.gwt.bank.shared.exceptions.InvalidDepositException;
import com.clouway.gwt.bank.shared.exceptions.InvalidWithdrawException;
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
    account = new Account(500.0);
  }

  @Test
  public void depositPositiveAmount() {
    account.deposit(100.0);
    assertEquals(600.0, account.getBalance());
  }

  @Test(expected = InvalidDepositException.class)
  public void depositZeroAmount() {
    account.deposit(0);
    assertEquals(balance, account.getBalance());
  }

  @Test(expected = ExceededDepositException.class)
  public void depositExcessAmount() {
    account.deposit(11000.0);
    assertEquals(balance, account.getBalance());
  }

  @Test(expected = InvalidDepositException.class)
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

  @Test(expected = InvalidWithdrawException.class)
  public void withdrawZeroAmount() {
    account.withdraw(0);
    assertEquals(balance, account.getBalance());
  }

  @Test(expected = InvalidWithdrawException.class)
  public void withdrawNegativeAmount()  {
    account.withdraw(-100.0);
    assertEquals(balance, account.getBalance());
  }
}
