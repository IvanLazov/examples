package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.shared.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class UserResultSetMapper implements ResultSetMapper<User> {
  public User map(ResultSet resultSet) {
    User user = null;

    try {
      user = new User(resultSet.getLong("userId"),resultSet.getString("username"),resultSet.getString("password"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return user;
  }
}
