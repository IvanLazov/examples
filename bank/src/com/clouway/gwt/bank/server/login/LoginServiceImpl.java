package com.clouway.gwt.bank.server.login;

import com.clouway.gwt.bank.client.LoginService;
import com.clouway.gwt.bank.shared.AuthorizedUser;
import com.clouway.gwt.bank.server.authorization.AuthorizationRepository;
import com.clouway.gwt.bank.shared.User;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameOrPasswordException;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;

import java.lang.reflect.Method;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class LoginServiceImpl extends XsrfProtectedServiceServlet implements LoginService {

  private final LoginRepository loginRepository;
  private final AuthorizationRepository authorizationRepository;
  private XsrfToken token;

  public LoginServiceImpl(LoginRepository loginRepository, AuthorizationRepository authorizationRepository) {
    this.loginRepository = loginRepository;
    this.authorizationRepository = authorizationRepository;
  }

  @Override
  protected void validateXsrfToken(RpcToken token, Method method) throws RpcTokenException {
    super.validateXsrfToken(token, method);
    this.token = (XsrfToken) token;
  }

  public AuthorizedUser loginUser(User user) {

    String tokenValue = token.getToken();

    User actualUser = loginRepository.getUser(user);

    if (actualUser == null) {
      throw new WrongUsernameOrPasswordException();
    }

    AuthorizedUser authorizedUser = new AuthorizedUser(tokenValue, actualUser.getUserId(), actualUser.getUsername());
    authorizationRepository.save(tokenValue, actualUser.getUserId(), actualUser.getUsername());

    return authorizedUser;
  }
}
