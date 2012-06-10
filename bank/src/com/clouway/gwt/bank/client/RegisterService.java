package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.shared.User;
import com.clouway.gwt.bank.shared.exceptions.UsernameAlreadyTakenException;
import com.clouway.gwt.bank.shared.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameException;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RemoteServiceRelativePath("registerService")
public interface RegisterService extends XsrfProtectedService {

  void registerUser(User user) throws WrongUsernameException, WrongPasswordException, UsernameAlreadyTakenException;
}
