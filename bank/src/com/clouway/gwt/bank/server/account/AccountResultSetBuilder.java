package com.clouway.gwt.bank.server.account;

import com.clouway.gwt.bank.server.ResultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountResultSetBuilder implements ResultSetBuilder<Account> {
  public Account build(ResultSet resultSet) {
    Account account = null;

    try {
      account = new Account(resultSet.getLong("accountId"),resultSet.getLong("userId"),resultSet.getDouble("balance"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return account;
  }
}
