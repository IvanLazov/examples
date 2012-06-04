package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.client.account.AccountRepository;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.gwt.bank.client.BankService;

public class BankServiceImpl extends RemoteServiceServlet implements BankService {

  private final AccountRepository repository;

  public BankServiceImpl(AccountRepository repository) {
    this.repository = repository;
  }

  public double deposit(double amount) {
    return 0;
  }

  public double withdraw(double amount) {
    return 0;
  }
}