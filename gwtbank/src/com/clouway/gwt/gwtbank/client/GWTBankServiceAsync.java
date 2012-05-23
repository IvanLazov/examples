package com.clouway.gwt.gwtbank.client;

import com.clouway.gwt.gwtbank.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTBankServiceAsync {

  void register(User user, AsyncCallback<Void> callback);
}
