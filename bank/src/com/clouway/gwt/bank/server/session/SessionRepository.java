package com.clouway.gwt.bank.server.session;

import com.clouway.gwt.bank.shared.AuthorizedUser;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface SessionRepository {

  void createSession(String token, long userId, String username);
  void deleteSession(String token);
  AuthorizedUser getUser(String token);
}
