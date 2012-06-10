package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.RegisterServiceAsync;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.shared.User;
import com.clouway.gwt.bank.shared.exceptions.UsernameAlreadyTakenException;
import com.clouway.gwt.bank.shared.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameException;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class RegisterPresenter implements Presenter, RegisterView.Presenter {

  private final RegisterServiceAsync rpcService;
  private final RegisterView view;

  public RegisterPresenter(RegisterServiceAsync rpcService, RegisterView view) {
    this.rpcService = rpcService;
    this.view = view;
  }

  public void go(HasWidgets container) {
    this.view.setPresenter(this);
    container.add((Widget) view);
  }

  public void registerUser() {

    User user = view.getUser();

    if (!matches(user.getUsername(), "^[a-zA-Z0-9]{1,20}$")) {
      view.wrongUsernameNotification();
      view.clearPasswordField();
      return;
    }

    if (!matches(user.getPassword(), "^[a-zA-Z0-9]{6,20}$")) {
      view.wrongPasswordNotification();
      view.clearPasswordField();
      return;
    }

    rpcService.registerUser(user, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {
        if (caught instanceof WrongUsernameException) {
          view.wrongUsernameNotification();
          view.clearPasswordField();
        }

        if (caught instanceof WrongPasswordException) {
          view.wrongPasswordNotification();
          view.clearPasswordField();
        }

        if (caught instanceof UsernameAlreadyTakenException) {
          view.usernameAlreadyTakenNotification();
          view.clearUsernameField();
          view.clearPasswordField();
        }
      }

      public void onSuccess(Void result) {
        view.registrationSuccessfulNotification();
        view.clearUsernameField();
        view.clearPasswordField();
      }

    });
  }

  private boolean matches(String input, String pattern) {
    return RegExp.compile(pattern).test(input);
  }
}
