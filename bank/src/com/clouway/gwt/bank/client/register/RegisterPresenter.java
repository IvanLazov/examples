package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.BankServiceAsync;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.client.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameException;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class RegisterPresenter implements Presenter, RegisterView.Presenter {

  private final BankServiceAsync rpcService;
  private final RegisterView view;
  private final RegisterNotificationMessages notification;

  public RegisterPresenter(BankServiceAsync rpcService, RegisterView view, RegisterNotificationMessages notification) {
    this.rpcService = rpcService;
    this.view = view;
    this.notification = notification;
  }

  public void go(HasWidgets container) {
    this.view.setPresenter(this);
    container.add((Widget) view);
  }

  public void registerUser() {

    User user = view.getUser();

    if (!matches(user.getUsername(),"^[a-zA-Z0-9]{1,20}$")) {
      view.setNotification(notification.wrongUsernameMessage());
      view.clearPasswordField();
      return;
    }

    if (!matches(user.getPassword(),"^[a-zA-Z0-9]{6,20}$")) {
      view.setNotification(notification.wrongPasswordMessage());
      view.clearPasswordField();
      return;
    }

    rpcService.registerUser(user, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {

        if (caught instanceof WrongUsernameException) {
          view.setNotification(notification.wrongUsernameMessage());
          view.clearPasswordField();
        }

        if (caught instanceof WrongPasswordException) {
          view.setNotification(notification.wrongPasswordMessage());
          view.clearPasswordField();
        }
      }

      public void onSuccess(Void result) {
        view.setNotification(notification.successfulRegistrationMessage());
        view.clearUsernameField();
        view.clearPasswordField();
      }
    });
  }

  private boolean matches(String input, String pattern) {
    return RegExp.compile(pattern).test(input);
  }
}
