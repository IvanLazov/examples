package com.clouway.gwt.bank.client.account;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountViewImpl extends Composite implements AccountView {
  private Presenter presenter;

  interface AccountViewImplUiBinder extends UiBinder<Widget, AccountViewImpl> {}
  private static AccountViewImplUiBinder uiBinder = GWT.create(AccountViewImplUiBinder.class);

  @UiField
  Label balance;

  @UiField
  TextBox input;

  @UiField
  Label message;

  @UiField
  Button deposit;

  @UiField
  Button withdraw;

  @UiField
  Button logout;

  public AccountViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void updatedBalanceNotification(String amount) {
    this.balance.setText(amount);
  }

  public void clearInputField() {
    input.setText("");
  }

  public void successfulDepositNotification() {
    message.setText("Deposit was successful!");
  }

  public void zeroDepositNotification() {
    message.setText("Zero amount cannot be deposited!");
  }

  public void exceededDepositNotification() {
    message.setText("You cannot deposit amount bigger than 10,000");
  }

  public void incorrectInputNotification() {
    message.setText("You have entered invalid amount!");
  }

  public void successfulWithdrawNotification() {
    message.setText("Withdraw was successful!");
  }

  public void zeroWithdrawNotification() {
    message.setText("Zero amount cannot be withdraw!");
  }

  public void insufficientFundsNotification() {
    message.setText("Cannot withdraw! Insufficient funds!");
  }

  public void negativeWithdrawNotification() {
    message.setText("Negative amount not allowed to withdraw!");
  }

  public void negativeDepositNotification() {
    message.setText("Negative amount not allowed to deposit!");
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public String getEnteredAmount() {
    return input.getText();
  }

  @UiHandler("withdraw")
  public void onWithdrawButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.withdraw();
    }
  }

  @UiHandler("deposit")
  public void onDepositButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.deposit();
    }
  }

  @UiHandler("logout")
  public void onLogoutButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.logoutUser();
    }
  }
}