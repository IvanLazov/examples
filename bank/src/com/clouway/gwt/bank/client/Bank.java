package com.clouway.gwt.bank.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.RootPanel;

public class Bank implements EntryPoint {

  public void onModuleLoad() {

    XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync)GWT.create(XsrfTokenService.class);
    ((ServiceDefTarget)xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
    xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
      @Override
      public void onFailure(Throwable caught) {

      }

      @Override
      public void onSuccess(XsrfToken result) {



        LoginServiceAsync loginRpcService = GWT.create(LoginService.class);
        RegisterServiceAsync registerRpcService = GWT.create(RegisterService.class);
        AccountServiceAsync bankRpcService = GWT.create(AccountService.class);
        EventBus eventBus = new SimpleEventBus();

        AppController controller = new AppController(registerRpcService, loginRpcService, bankRpcService, eventBus);
        controller.go(RootPanel.get());
      }
    });






  }
}
