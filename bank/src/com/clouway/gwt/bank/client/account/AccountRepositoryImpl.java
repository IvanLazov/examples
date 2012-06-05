package com.clouway.gwt.bank.client.account;

import com.clouway.gwt.bank.server.AccountResultSetMapper;
import com.clouway.gwt.bank.server.DatabaseHelper;
import com.clouway.gwt.bank.server.ResultSetMapper;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountRepositoryImpl implements AccountRepository {

  private ResultSetMapper<Account> mapper = new AccountResultSetMapper();
  private final DatabaseHelper databaseHelper;

  public AccountRepositoryImpl(DatabaseHelper databaseHelper) {
    this.databaseHelper = databaseHelper;
  }

  public Account getAccount(long userId) {
    return databaseHelper.executeQuery("SELECT * FROM account WHERE userId=?", mapper, userId);
  }

  public void updateAccount(long userId, double amount) {
    databaseHelper.executeQuery("UPDATE account SET balance=? WHERE userId=?", amount, userId);
  }
}
