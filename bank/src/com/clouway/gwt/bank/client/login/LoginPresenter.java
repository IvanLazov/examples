package com.clouway.gwt.bank.client.login;

import com.clouway.gwt.bank.client.LoginServiceAsync;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.shared.AuthorizedUser;
import com.clouway.gwt.bank.shared.User;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameOrPasswordException;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class LoginPresenter implements Presenter, LoginView.Presenter {

  private final LoginServiceAsync loginRpcService;
  private final LoginView loginView;

  public LoginPresenter(LoginServiceAsync loginRpcService, LoginView loginView) {

    this.loginRpcService = loginRpcService;
    this.loginView = loginView;
  }

  public void go(HasWidgets container) {
    container.add((Widget) loginView);
    this.loginView.setPresenter(this);
  }

  public void loginUser() {

    User user = loginView.getUser();

    if (!matches(user.getUsername(), "^[a-zA-Z0-9]{1,20}$") || !matches(user.getPassword(), "^[a-zA-Z0-9]{6,20}$")) {
      loginView.wrongUsernameOrPasswordNotification();
      return;
    }

    loginRpcService.loginUser(user, new AsyncCallback<AuthorizedUser>() {

      public void onFailure(Throwable caught) {
        if (caught instanceof WrongUsernameOrPasswordException) {
          loginView.wrongUsernameOrPasswordNotification();
        }
      }

      public void onSuccess(AuthorizedUser result) {
        loginView.clearUsernameField();
        loginView.clearPasswordField();
        History.newItem("account");
      }
    });
  }

  private boolean matches(String input, String pattern) {
    return RegExp.compile(pattern).test(input);
  }
}
