package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.register.exceptions.InvalidPasswordException;
import com.clouway.gwt.bank.client.register.exceptions.InvalidUsernameException;
import com.clouway.gwt.bank.shared.User;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface RegisterView {

  public interface Presenter {
    void registerUser() throws InvalidUsernameException, InvalidPasswordException;
  }

  User getUser();
  void clearFields();
  void setNotification(String message);
  void setPresenter(Presenter presenter);
}
