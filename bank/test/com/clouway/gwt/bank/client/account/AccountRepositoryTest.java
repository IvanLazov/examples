package com.clouway.gwt.bank.client.account;

import com.clouway.gwt.bank.server.AccountResultSetMapper;
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

  private AccountRepositoryImpl repository;
  private final DatabaseHelper databaseHelper = new DatabaseHelper();
  private final User user = new User(1, "Test", "password");
  private final Account account = new Account(1, user.getUserId(), 500.0);

  @Before
  public void setUp() {
    repository = new AccountRepositoryImpl(databaseHelper);
    databaseHelper.executeQuery("DELETE FROM account");
    databaseHelper.executeQuery("DELETE FROM user");
  }

  @Test
  public void getAccountByUserId() {

    insertInDatabase();

    assertThat(account, is(equalTo(repository.getAccount(1))));
  }

  @Test
  public void updateAccountByUserId() {

    insertInDatabase();

    repository.updateAccount(user.getUserId(), 500.0);
    Account actual = databaseHelper.executeQuery("SELECT * FROM account WHERE userId=?", new AccountResultSetMapper(), user.getUserId());

    assertThat(500.0, is(equalTo(actual.getBalance())));
  }

  private void insertInDatabase() {
    databaseHelper.executeQuery("INSERT INTO user(userId,username,password) VALUES(?,?,?)", user.getUserId(), user.getUsername(), user.getPassword());
    databaseHelper.executeQuery("INSERT INTO account(accountId,userId,balance) VALUES(?,?,?)", 1, user.getUserId(), account.getBalance());
  }
}
