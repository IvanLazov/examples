package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.client.BankService;
import com.clouway.gwt.bank.client.account.Account;
import com.clouway.gwt.bank.client.account.AccountRepository;
import com.clouway.gwt.bank.shared.User;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class BaseServiceImplTest {

  private Mockery context = new JUnit4Mockery();
  private AccountRepository accountRepository = context.mock(AccountRepository.class);

  @Test
  public void depositPositiveAmount() {
    BankService bankService = new BankServiceImpl(accountRepository);

    final User user = new User("Test", "password");
    final Account account = new Account(1, 1, 500.0);

    context.checking(new Expectations(){{
      oneOf(accountRepository).getAccount(user);
      will(returnValue(account));

      oneOf(accountRepository).updateAccount(user, 100);
    }});

    bankService.deposit(100);
  }
}
