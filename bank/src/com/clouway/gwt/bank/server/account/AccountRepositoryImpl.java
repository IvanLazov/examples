package com.clouway.gwt.bank.server.account;

import com.clouway.gwt.bank.server.DatabaseHelper;
import com.clouway.gwt.bank.server.ResultSetBuilder;
import com.google.inject.Inject;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountRepositoryImpl implements AccountRepository {

  private final DatabaseHelper databaseHelper;
  private ResultSetBuilder<Account> builder = new AccountResultSetBuilder();

  @Inject
  public AccountRepositoryImpl(DatabaseHelper databaseHelper) {
    this.databaseHelper = databaseHelper;
  }

  public Account getAccount(long userId) {
    return databaseHelper.executeQuery("SELECT * FROM account WHERE userId=?", builder, userId);
  }

  public void updateAccount(long userId, double amount) {
    databaseHelper.executeQuery("UPDATE account SET balance=? WHERE userId=?", amount, userId);
  }

  public void createAccount(long userId) {
    databaseHelper.executeQuery("INSERT INTO account(userId,balance) VALUES(?,?)", userId, 0.0);
  }
}
