package com.clouway.gwt.gwtbank.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.gwt.gwtbank.client.GWTBankService;

public class GWTBankServiceImpl extends RemoteServiceServlet implements GWTBankService {
  // Implementation of sample interface method
  public String getMessage(String msg) {
    return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
  }
}