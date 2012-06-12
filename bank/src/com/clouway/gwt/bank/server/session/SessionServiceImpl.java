package com.clouway.gwt.bank.server.session;

import com.clouway.gwt.bank.client.SessionService;
import com.clouway.gwt.bank.shared.AuthorizedUser;
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
public class SessionServiceImpl extends XsrfProtectedServiceServlet implements SessionService {

  private final SessionRepository sessionRepository;
  private XsrfToken token;

  @Inject
  public SessionServiceImpl(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  @Override
  protected void validateXsrfToken(RpcToken token, Method method) throws RpcTokenException {
    super.validateXsrfToken(token, method);
    this.token = (XsrfToken) token;
  }

  public boolean isUserAuthorized() {

    AuthorizedUser user = sessionRepository.getUser(token.getToken());
    return user != null;
  }

  public void logoutUser() {
    sessionRepository.deleteSession(token.getToken());
  }
}
