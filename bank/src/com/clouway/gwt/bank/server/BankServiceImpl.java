package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.client.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameException;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameOrPasswordException;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.gwt.bank.client.BankService;

public class BankServiceImpl extends RemoteServiceServlet implements BankService {

  public void registerUser(User user) throws WrongUsernameException, WrongPasswordException {
  }

  public User loginUser(User user) throws WrongUsernameOrPasswordException {
    return null;
  }
}