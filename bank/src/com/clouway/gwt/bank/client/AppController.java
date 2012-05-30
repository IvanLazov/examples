package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.client.register.RegisterNotificationMessages;
import com.clouway.gwt.bank.client.register.RegisterNotificationMessagesImpl;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.client.register.RegisterPresenter;
import com.clouway.gwt.bank.client.register.RegisterView;
import com.clouway.gwt.bank.client.register.RegisterViewImpl;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AppController implements Presenter, ValueChangeHandler<String> {

  private final BankServiceAsync rpcService;
  private HasWidgets container;
  private RegisterView registerView;
  private RegisterNotificationMessages registerNotificationMessages;

  public AppController(BankServiceAsync rpcService) {
    this.rpcService = rpcService;
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

        if (registerNotificationMessages == null) {
          registerNotificationMessages = new RegisterNotificationMessagesImpl();
        }

        new RegisterPresenter(rpcService, registerView, registerNotificationMessages).go(container);
      }
    }
  }
}
