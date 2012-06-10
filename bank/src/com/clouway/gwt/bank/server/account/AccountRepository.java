package com.clouway.gwt.bank.server.account;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface AccountRepository {

  Account getAccount(long userId);
  void updateAccount(long userId, double amount);
  void createAccount(long userId);
}
