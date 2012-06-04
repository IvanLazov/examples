package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
  void registerUser(User user, AsyncCallback<Void> async);

  void loginUser(User user, AsyncCallback<User> async);
}
