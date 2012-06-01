package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.client.account.AccountPresenter;
import com.clouway.gwt.bank.client.account.AccountView;
import com.clouway.gwt.bank.client.account.AccountViewImpl;
import com.clouway.gwt.bank.client.login.LoginEvent;
import com.clouway.gwt.bank.client.login.LoginPresenter;
import com.clouway.gwt.bank.client.login.LoginView;
import com.clouway.gwt.bank.client.login.LoginViewImpl;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.client.register.RegisterPresenter;
import com.clouway.gwt.bank.client.register.RegisterView;
import com.clouway.gwt.bank.client.register.RegisterViewImpl;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AppController implements Presenter, ValueChangeHandler<String> {

  private final BankServiceAsync rpcService;
  private HasWidgets container;
  private RegisterView registerView;
  private LoginView loginView;
  private EventBus eventBus;
  private AccountView accountView;

  public AppController(BankServiceAsync rpcService, EventBus eventBus) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
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

      if (token.equals("register")) {
        if (registerView == null) {
          registerView = new RegisterViewImpl();
        }
        new RegisterPresenter(rpcService, registerView).go(container);
      }

      if (token.equals("login")) {

        if (loginView == null) {
          loginView = new LoginViewImpl();
        }
        new LoginPresenter(rpcService, loginView).go(container);
      }

      if (token.equals("userpage")) {
        if (accountView == null) {
          accountView = new AccountViewImpl();
        }
        new AccountPresenter(rpcService, accountView).go(container);
      }
    }
  }
}
