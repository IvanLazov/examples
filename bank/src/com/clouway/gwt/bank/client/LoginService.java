package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.shared.AuthorizedUser;
import com.clouway.gwt.bank.shared.User;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameOrPasswordException;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RemoteServiceRelativePath("loginService")
public interface LoginService extends XsrfProtectedService {

  AuthorizedUser loginUser(User user) throws WrongUsernameOrPasswordException;
}
