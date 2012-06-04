package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.client.UserService;
import com.clouway.gwt.bank.client.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameException;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameOrPasswordException;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

  public void registerUser(User user) throws WrongUsernameException, WrongPasswordException {

  }

  public User loginUser(User user) throws WrongUsernameOrPasswordException {
    return null;
  }
}
