package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.shared.User;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface RegisterView {

  public interface Presenter {
    void registerUser();
  }

  User getUser();
  void clearPasswordField();
  void clearUsernameField();
  void setPresenter(Presenter presenter);
  void showWrongUsernameNotification();
  void showWrongPasswordNotification();
  void showSuccessfulRegistrationNotification();
}
