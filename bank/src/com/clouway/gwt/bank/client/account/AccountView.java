package com.clouway.gwt.bank.client.account;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface AccountView {

  public interface Presenter {
    void deposit();
    void withdraw();
  }

  void setPresenter(Presenter presenter);
  void updateBalance(String amount);
  void clearInputField();
  void successfulDeposit();
  void zeroAmountDeposit();
  void exceededDeposit();
  void negativeDeposit();
  void invalidInput();
  void successfulWithdraw();
  void zeroAmountWithdraw();
  void insufficientFunds();
  void negativeWithdraw();
  String getEnteredAmount();
}
