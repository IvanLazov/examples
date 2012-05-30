package com.clouway.gwt.bank.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class LoginViewImpl extends Composite {
  interface LoginViewImplUiBinder extends UiBinder<Widget, LoginViewImpl> {}
  private static LoginViewImplUiBinder uiBinder = GWT.create(LoginViewImplUiBinder.class);

  public LoginViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}