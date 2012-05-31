package com.clouway.gwt.bank.client.login;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class LoginEvent extends GwtEvent<LoginEventHandler> {
  public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();

  public Type<LoginEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(LoginEventHandler handler) {
    handler.onLogin(this);
  }
}
