package com.clouway.gwt.bank.client.login;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface LoginEventHandler extends EventHandler {
  void onLogin(LoginEvent event);
}
