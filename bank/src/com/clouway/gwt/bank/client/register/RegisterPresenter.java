package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.BankServiceAsync;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

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

  public void go(HasWidgets widgets) {

  }

  public void onRegisterUser(User user) {
    rpcService.registerUser(user, new AsyncCallback<User>() {

      public void onFailure(Throwable caught) {
      }

      public void onSuccess(User result) {
      }
    });
  }
}
