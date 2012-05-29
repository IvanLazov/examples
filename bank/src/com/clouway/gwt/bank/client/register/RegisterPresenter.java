package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.BankServiceAsync;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.client.register.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.client.register.exceptions.WrongUsernameException;
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

  public RegisterPresenter(BankServiceAsync rpcService, RegisterView view) {
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
      throw new WrongUsernameException();
    }

    if (!matches(user.getPassword(), "^[a-zA-Z0-9]{6,20}$")) {
      throw new WrongPasswordException();
    }

    rpcService.registerUser(user, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {

      }

      public void onSuccess(Void result) {
        view.clearFields();
        view.setNotification("Registration was successful!");
      }
    });
  }

  private boolean matches(String input, String pattern) {
    return RegExp.compile(pattern).test(input);
  }
}
