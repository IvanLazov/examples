package com.clouway.gwt.bank.server.session;

import com.clouway.gwt.bank.server.ResultSetBuilder;
import com.clouway.gwt.bank.shared.AuthorizedUser;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AuthorizedUserResultSetBuilder implements ResultSetBuilder<AuthorizedUser> {

  public AuthorizedUser build(ResultSet resultSet) {
    AuthorizedUser authorizedUser = null;

    try {
      authorizedUser = new AuthorizedUser(resultSet.getString("token"), resultSet.getLong("userId"), resultSet.getString("username"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return authorizedUser;
  }
}
