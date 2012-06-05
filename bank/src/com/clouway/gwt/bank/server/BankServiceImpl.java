package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.client.account.Account;
import com.clouway.gwt.bank.client.account.AccountRepository;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.gwt.bank.client.BankService;

public class BankServiceImpl extends RemoteServiceServlet implements BankService {

  private final AccountRepository repository;
  private User user;

  public BankServiceImpl(AccountRepository repository, User user) {
    this.repository = repository;
    this.user = user;
  }

  public double deposit(double amount) {
    Account account = repository.getAccount(user.getUserId());
    account.deposit(amount);
    repository.updateAccount(user.getUserId(), account.getBalance());
    return account.getBalance();
  }

  public double withdraw(double amount) {
    Account account = repository.getAccount(user.getUserId());
    account.withdraw(amount);
    repository.updateAccount(user.getUserId(), account.getBalance());
    return account.getBalance();
  }
}