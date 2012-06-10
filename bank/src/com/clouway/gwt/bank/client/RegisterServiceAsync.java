package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RegisterServiceAsync {
  void registerUser(User user, AsyncCallback<Void> callback);
}
