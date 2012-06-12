package com.clouway.gwt.bank.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.RootPanel;

public class Bank implements EntryPoint {

  public void onModuleLoad() {

    XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync) GWT.create(XsrfTokenService.class);
    ((ServiceDefTarget) xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
    xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
      public void onFailure(Throwable caught) {
      }

      public void onSuccess(XsrfToken token) {

        LoginServiceAsync loginRpcService = GWT.create(LoginService.class);
        ((HasRpcToken) loginRpcService).setRpcToken(token);

        RegisterServiceAsync registerRpcService = GWT.create(RegisterService.class);
        ((HasRpcToken) registerRpcService).setRpcToken(token);

        AccountServiceAsync accountRpcService = GWT.create(AccountService.class);
        ((HasRpcToken) accountRpcService).setRpcToken(token);

        SessionServiceAsync sessionRpcService = GWT.create(SessionService.class);
        ((HasRpcToken) sessionRpcService).setRpcToken(token);

        AppController controller = new AppController(loginRpcService, registerRpcService, accountRpcService, sessionRpcService);
        controller.go(RootPanel.get());
      }
    });
  }
}
