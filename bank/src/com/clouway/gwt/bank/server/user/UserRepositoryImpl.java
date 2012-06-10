package com.clouway.gwt.bank.server.user;

import com.clouway.gwt.bank.server.DatabaseHelper;
import com.clouway.gwt.bank.server.ResultSetBuilder;
import com.clouway.gwt.bank.shared.User;
import com.google.inject.Inject;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class UserRepositoryImpl implements UserRepository {

  private final DatabaseHelper databaseHelper;
  private final ResultSetBuilder<User> builder = new UserResultSetBuilder();

  @Inject
  public UserRepositoryImpl(DatabaseHelper databaseHelper) {
    this.databaseHelper = databaseHelper;
  }

  public long register(User user) {
    return databaseHelper.executeQuery("INSERT INTO user(username,password) VALUES(?,?)", user.getUsername(), user.getPassword());
  }

  public User getUser(String username) {
    return databaseHelper.executeQuery("SELECT * FROM user WHERE username=?", builder, username);
  }
}
