package com.clouway.gwt.GWTBank.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.clouway.gwt.GWTBank.client.GWTBankService;

public class GWTBankServiceImpl extends RemoteServiceServlet implements GWTBankService {
  // Implementation of sample interface method
  public String getMessage(String msg) {
    return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
  }
}