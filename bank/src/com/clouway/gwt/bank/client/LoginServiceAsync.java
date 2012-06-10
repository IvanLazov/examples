package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.shared.AuthorizedUser;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

  void loginUser(User user, AsyncCallback<AuthorizedUser> async);
}
