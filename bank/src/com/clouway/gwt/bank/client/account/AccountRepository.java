package com.clouway.gwt.bank.client.account;

import com.clouway.gwt.bank.shared.User;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface AccountRepository {

  Account getAccount(User user);
  void updateAccount(User user, double amount);
}
