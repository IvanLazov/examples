package com.clouway.gwt.gwtbank.client;

import com.clouway.gwt.gwtbank.shared.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GWTBankService")
public interface GWTBankService extends RemoteService {
  void register(User user);
}
