package com.clouway.gwt.bank.client.account;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface AccountRepository {

  Account getAccount(long userId);
  void updateAccount(long userId, double amount);
}
