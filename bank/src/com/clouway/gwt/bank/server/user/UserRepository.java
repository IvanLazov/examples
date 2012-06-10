package com.clouway.gwt.bank.server.user;

import com.clouway.gwt.bank.shared.User;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface UserRepository {

  long register(User user);
  User getUser(String username);
}
