package com.clouway.gwt.bank.client.login;

import com.clouway.gwt.bank.client.BankServiceAsync;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class LoginEventHandlerImpl implements LoginEventHandler {

  private final BankServiceAsync rpcService;
  private final LoginView view;

  public LoginEventHandlerImpl(BankServiceAsync rpcService, LoginView view) {
    this.rpcService = rpcService;
    this.view = view;
  }

  public void onLogin(LoginEvent event) {

  }
}
