package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.client.PageService;
import com.clouway.gwt.bank.server.session.SessionRepository;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Singleton
public class PageServiceImpl extends XsrfProtectedServiceServlet implements PageService {

  private SessionRepository sessionRepository;
  private RpcToken token;

  @Inject
  public PageServiceImpl(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  @Override
  protected void validateXsrfToken(RpcToken token, Method method) throws RpcTokenException {
    super.validateXsrfToken(token, method);
    this.token = token;
  }

  public List<String> getPages() {

    List<String> pages = new ArrayList<String>();

    if (sessionRepository.getUser(token.toString()) == null) {
      pages.add("register");
      pages.add("login");
    } else {
      pages.add("register");
      pages.add("login");
      pages.add("account");
    }

    return pages;
  }
}
