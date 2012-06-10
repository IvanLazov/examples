package com.clouway.gwt.bank.server.session;

import com.clouway.gwt.bank.server.DatabaseHelper;
import com.clouway.gwt.bank.shared.AuthorizedUser;
import com.google.inject.Inject;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class SessionRepositoryImpl implements SessionRepository {

  private final DatabaseHelper databaseHelper;
  private final AuthorizedUserResultSetBuilder builder = new AuthorizedUserResultSetBuilder();

  @Inject
  public SessionRepositoryImpl(DatabaseHelper databaseHelper) {
    this.databaseHelper = databaseHelper;
  }

  public void createSession(String token, long userId, String username) {
    databaseHelper.executeQuery("INSERT INTO session(token,userId,username) VALUES(?,?,?)", token, userId, username);
  }

  public void deleteSession(String token) {
    databaseHelper.executeQuery("DELETE FROM session WHERE token=?", token);
  }

  public AuthorizedUser getUser(String token) {
    return databaseHelper.executeQuery("SELECT * FROM session WHERE token=?", builder, token);
  }
}
