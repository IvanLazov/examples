package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.client.account.AccountPresenter;
import com.clouway.gwt.bank.client.account.AccountView;
import com.clouway.gwt.bank.client.account.AccountViewImpl;
import com.clouway.gwt.bank.client.login.LoginPresenter;
import com.clouway.gwt.bank.client.login.LoginView;
import com.clouway.gwt.bank.client.login.LoginViewImpl;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.client.register.RegisterPresenter;
import com.clouway.gwt.bank.client.register.RegisterView;
import com.clouway.gwt.bank.client.register.RegisterViewImpl;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AppController implements Presenter, ValueChangeHandler<String> {

  private HasWidgets container;
  private final LoginServiceAsync loginRpcService;
  private final RegisterServiceAsync registerRpcService;
  private final AccountServiceAsync accountRpcService;
  private final SessionServiceAsync sessionRpcService;

  private LoginView loginView;
  private RegisterView registerView;
  private AccountView accountView;

  public AppController(LoginServiceAsync loginRpcService, RegisterServiceAsync registerRpcService, AccountServiceAsync accountRpcService, SessionServiceAsync sessionRpcService) {

    this.loginRpcService = loginRpcService;
    this.registerRpcService = registerRpcService;
    this.accountRpcService = accountRpcService;
    this.sessionRpcService = sessionRpcService;
    bind();
  }

  private void bind() {
    History.addValueChangeHandler(this);
  }

  public void go(HasWidgets container) {
    this.container = container;

    if ("".equals(History.getToken())) {
      History.newItem("register");
    } else {
      History.fireCurrentHistoryState();
    }
  }

  public void onValueChange(ValueChangeEvent<String> event) {

    container.clear();
    String token = event.getValue();

    if (token != null) {
      if (token.equals("login")) {
        sessionRpcService.isUserAuthorized(new AsyncCallback<Boolean>() {
          public void onFailure(Throwable caught) {
          }

          public void onSuccess(Boolean result) {
            if (result) {
              History.newItem("account");
            } else {
              if (loginView == null) {
                loginView = new LoginViewImpl();
              }
              new LoginPresenter(loginRpcService, loginView).go(container);
            }
          }
        });
      }

      if (token.equals("register")) {

        sessionRpcService.isUserAuthorized(new AsyncCallback<Boolean>() {
          public void onFailure(Throwable caught) {
          }

          public void onSuccess(Boolean result) {
            if (result) {
              History.newItem("account");
            } else {
              if (registerView == null) {
                registerView = new RegisterViewImpl();
              }
              new RegisterPresenter(registerRpcService, registerView).go(container);
            }
          }
        });
      }

      if (token.equals("account")) {
        sessionRpcService.isUserAuthorized(new AsyncCallback<Boolean>() {
          public void onFailure(Throwable caught) {
          }

          public void onSuccess(Boolean result) {
            if (result) {
              if (accountView == null) {
                accountView = new AccountViewImpl();
              }
              new AccountPresenter(accountRpcService, sessionRpcService, accountView).go(container);
            }
          }
        });
      }
    }
  }
}
