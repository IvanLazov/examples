package com.clouway.gwt.bank.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RemoteServiceRelativePath("sessionService")
public interface SessionService extends XsrfProtectedService {
  boolean isUserAuthorized();
  void logoutUser();
}
