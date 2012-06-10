package com.clouway.gwt.bank.client.login;

import com.clouway.gwt.bank.shared.User;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface LoginView {

  public interface Presenter {
    void loginUser();
  }

  User getUser();
  void wrongUsernameOrPasswordNotification();
  void setPresenter(Presenter presenter);
}
