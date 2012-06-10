package com.clouway.gwt.bank.server.login;

import com.clouway.gwt.bank.client.LoginService;
import com.clouway.gwt.bank.server.session.SessionRepository;
import com.clouway.gwt.bank.server.user.UserRepository;
import com.clouway.gwt.bank.shared.AuthorizedUser;
import com.clouway.gwt.bank.shared.User;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameOrPasswordException;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.lang.reflect.Method;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Singleton
public class LoginServiceImpl extends XsrfProtectedServiceServlet implements LoginService {

  private final UserRepository userRepository;
  private final SessionRepository sessionRepository;
  private XsrfToken token;

  @Inject
  public LoginServiceImpl(UserRepository userRepository, SessionRepository sessionRepository) {
    this.userRepository = userRepository;
    this.sessionRepository = sessionRepository;
  }

  @Override
  protected void validateXsrfToken(RpcToken token, Method method) throws RpcTokenException {
    super.validateXsrfToken(token, method);
    this.token = (XsrfToken) token;
  }

  public AuthorizedUser loginUser(User user) {

    User returnedUser = userRepository.getUser(user.getUsername());

    if (returnedUser == null) {
      throw new WrongUsernameOrPasswordException();
    }

    sessionRepository.createSession(token.getToken(), returnedUser.getUserId(), returnedUser.getUsername());
    AuthorizedUser authorizedUser = sessionRepository.getUser(token.getToken());

    return authorizedUser;
  }
}
