package com.clouway.gwt.bank.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SessionServiceAsync {
  void isUserAuthorized(AsyncCallback<Boolean> async);

  void logoutUser(AsyncCallback<Void> async);
}
