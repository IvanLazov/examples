package com.clouway.gwt.bank.client.login;

import com.clouway.gwt.bank.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class LoginViewImpl extends Composite implements LoginView {
  interface LoginViewImplUiBinder extends UiBinder<Widget, LoginViewImpl> {}
  private static LoginViewImplUiBinder uiBinder = GWT.create(LoginViewImplUiBinder.class);

  private Presenter presenter;

  @UiField
  TextBox username;

  @UiField
  PasswordTextBox password;

  @UiField
  Button login;

  @UiField
  Button goToRegister;

  @UiField
  Label notification;

  public LoginViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @UiHandler("goToRegister")
  public void onGoToRegisterButtonClicked(ClickEvent event) {
    History.newItem("register");
  }

  @UiHandler("login")
  public void onLoginButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.loginUser();
    }
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public User getUser() {
    return new User(username.getText(), password.getText());
  }

  public void showWrongUsernameOrPasswordNotification() {
    notification.setText("Wrong username or password! Try again.");
  }
}