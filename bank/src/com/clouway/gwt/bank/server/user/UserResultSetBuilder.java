package com.clouway.gwt.bank.server.user;

import com.clouway.gwt.bank.server.ResultSetBuilder;
import com.clouway.gwt.bank.shared.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class UserResultSetBuilder implements ResultSetBuilder<User> {
  public User build(ResultSet resultSet) {
    User user = null;

    try {
      user = new User(resultSet.getLong("userId"),resultSet.getString("username"),resultSet.getString("password"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return user;
  }
}
