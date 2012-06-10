package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.client.login.LoginPresenter;
import com.clouway.gwt.bank.client.login.LoginViewImpl;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.client.register.RegisterPresenter;
import com.clouway.gwt.bank.client.register.RegisterViewImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank implements EntryPoint {

  public void onModuleLoad() {

    final Map<String, Presenter> userPresentersMap = new HashMap<String, Presenter>();

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

        final Map<String, Presenter> presentersMap = new HashMap<String, Presenter>();
        presentersMap.put("register", new RegisterPresenter(registerRpcService, new RegisterViewImpl()));
        presentersMap.put("login", new LoginPresenter(userPresentersMap, accountRpcService, loginRpcService, new LoginViewImpl()));

        PageServiceAsync pageRpcService = GWT.create(PageService.class);
        ((HasRpcToken) pageRpcService).setRpcToken(token);
        pageRpcService.getPages(new AsyncCallback<List<String>>() {
          public void onFailure(Throwable caught) {
          }

          public void onSuccess(List<String> pages) {
            for (String page : pages) {
              if (presentersMap.containsKey(page)) {
                userPresentersMap.put(page, presentersMap.get(page));
              }
            }

            AppController controller = new AppController(userPresentersMap);
            controller.go(RootPanel.get());
          }
        });
      }
    });
  }
}
