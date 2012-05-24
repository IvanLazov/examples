package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class RegisterViewImpl extends Composite implements RegisterView {

  interface RegisterViewImplUiBinder extends UiBinder<Widget, RegisterViewImpl> {}
  private static RegisterViewImplUiBinder uiBinder = GWT.create(RegisterViewImplUiBinder.class);

  private Presenter presenter;
  @UiField
  TextBox username;

  @UiField
  TextBox password;

  @UiField
  Button register;

  public RegisterViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @UiHandler("register")
  void onRegisterButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.onRegisterUser(new User(username.getText(), password.getText()));
    }
  }
}