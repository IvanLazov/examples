package com.clouway.gwt.bank.server.account;

import com.clouway.gwt.bank.server.DatabaseHelper;
import com.clouway.gwt.bank.shared.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountRepositoryTest {

  private AccountRepositoryImpl accountRepository;

  private DatabaseHelper databaseHelper = new DatabaseHelper();
  private User user = new User(1, "Test", "password");

  @Before
  public void setUp() {
    accountRepository = new AccountRepositoryImpl(databaseHelper);

    databaseHelper.executeQuery("DELETE FROM session");
    databaseHelper.executeQuery("DELETE FROM account");
    databaseHelper.executeQuery("DELETE FROM user");

    databaseHelper.executeQuery("INSERT INTO user(userId, username, password) VALUES(?,?,?)", user.getUserId(), user.getUsername(), user.getUsername());
  }

  @Test
  public void getAccountByUserId() {
    accountRepository.createAccount(user.getUserId());
    Account account = accountRepository.getAccount(user.getUserId());

    assertThat(user.getUserId(), is(equalTo(account.getUserId())));
    assertThat(0.0, is(equalTo(account.getBalance())));
  }

  @Test
  public void updateAccountByUserId() {
    accountRepository.createAccount(user.getUserId());
    accountRepository.updateAccount(user.getUserId(), 500.0);
    Account account = accountRepository.getAccount(user.getUserId());

    assertThat(user.getUserId(), is(equalTo(account.getUserId())));
    assertThat(500.0, is(equalTo(account.getBalance())));
  }
}
