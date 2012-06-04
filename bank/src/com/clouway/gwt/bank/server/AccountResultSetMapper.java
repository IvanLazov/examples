package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.client.account.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountResultSetMapper implements ResultSetMapper<Account> {
  public Account map(ResultSet resultSet) {
    Account account = null;

    try {
      account = new Account(resultSet.getLong("accountId"),resultSet.getLong("userId"),resultSet.getDouble("balance"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return account;
  }
}
