package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.shared.User;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface RegisterView {

  public interface Presenter {
    void onRegisterUser(User user);
  }

  void setPresenter(Presenter presenter);
}
