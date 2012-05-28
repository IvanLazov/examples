package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.register.exceptions.InvalidPasswordException;
import com.clouway.gwt.bank.client.register.exceptions.InvalidUsernameException;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
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
  PasswordTextBox password;

  @UiField
  Button register;

  @UiField
  Label notification;

  public RegisterViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public User getUser() {
    return new User(username.getText(), password.getText());
  }

  public void setNotification(String message) {
    this.notification.setText(message);
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @UiHandler("register")
  void onRegisterButtonClicked(ClickEvent event) {
    if (presenter != null) {

      try {
        presenter.registerUser();
      } catch (InvalidUsernameException exception) {
        notification.setText("Invalid username!");
      } catch (InvalidPasswordException exception) {
        notification.setText("Invalid password!");
      }

      clearFields();
    }
  }

  public void clearFields() {
    username.setText("");
    password.setText("");
  }
}