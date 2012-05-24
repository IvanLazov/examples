package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.gwt.bank.client.BankService;

public class BankServiceImpl extends RemoteServiceServlet implements BankService {

  public User registerUser(User user) {
    return null;
  }
}