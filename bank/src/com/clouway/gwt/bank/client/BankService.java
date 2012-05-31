package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.client.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameException;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameOrPasswordException;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("BankService")
public interface BankService extends RemoteService {
  void registerUser(User user) throws WrongUsernameException, WrongPasswordException;
  User loginUser(User user) throws WrongUsernameOrPasswordException;
}
