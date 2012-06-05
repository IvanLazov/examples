package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.client.account.Account;
import com.clouway.gwt.bank.client.account.AccountRepository;
import com.clouway.gwt.bank.shared.User;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class BankServiceImplTest {

  private BankServiceImpl bankService;
  private Mockery context = new JUnit4Mockery();
  private AccountRepository accountRepository = context.mock(AccountRepository.class);

  private final User user = new User(1, "Test", "password");
  private final Account account = new Account(1, user.getUserId(), 500.0);

  @Before
  public void setUp() {
    bankService = new BankServiceImpl(accountRepository, user);
  }

  @Test
  public void deposit() {

    final double depositAmount = 100;

    context.checking(new Expectations(){{
      oneOf(accountRepository).getAccount(user.getUserId());
      will(returnValue(account));

      oneOf(accountRepository).updateAccount(user.getUserId(), account.getBalance() + depositAmount);
    }});

    assertEquals(600.0, bankService.deposit(depositAmount));
  }

  @Test
  public void withdraw() {

    final double withdrawAmount = 100;

    context.checking(new Expectations(){{
      oneOf(accountRepository).getAccount(user.getUserId());
      will(returnValue(account));

      oneOf(accountRepository).updateAccount(user.getUserId(), account.getBalance() - withdrawAmount);
    }});

    assertEquals(400.0, bankService.withdraw(withdrawAmount));
  }
}
