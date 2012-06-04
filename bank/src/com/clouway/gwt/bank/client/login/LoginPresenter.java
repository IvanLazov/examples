package com.clouway.gwt.bank.client.login;

import com.clouway.gwt.bank.client.UserServiceAsync;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameOrPasswordException;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class LoginPresenter implements Presenter, LoginView.Presenter {

  private final UserServiceAsync rpcService;
  private final LoginView view;

  public LoginPresenter(UserServiceAsync rpcService, LoginView view) {
    this.rpcService = rpcService;
    this.view = view;
  }

  public void go(HasWidgets container) {
    container.add((Widget) view);
    this.view.setPresenter(this);
  }

  public void loginUser() {

    User user = view.getUser();

    if (!matches(user.getUsername(),"^[a-zA-Z0-9]{1,20}$") || !matches(user.getPassword(), "^[a-zA-Z0-9]{6,20}$")) {
      view.showWrongUsernameOrPasswordNotification();
      return;
    }

    rpcService.loginUser(user, new AsyncCallback<User>() {
      public void onFailure(Throwable caught) {
        if (caught instanceof WrongUsernameOrPasswordException) {
          view.showWrongUsernameOrPasswordNotification();
        }
      }

      public void onSuccess(User result) {
        History.newItem("userpage");
      }
    });
  }

  private boolean matches(String input, String pattern) {
    return RegExp.compile(pattern).test(input);
  }
}
