package com.clouway.gwt.bank.client.account;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface AccountView {

  public interface Presenter {
    void deposit();
    void withdraw();
    void logoutUser();
  }

  void setPresenter(Presenter presenter);
  void clearInputField();
  String getEnteredAmount();

  void updatedBalanceNotification(String amount);
  void successfulDepositNotification();
  void zeroDepositNotification();
  void exceededDepositNotification();
  void negativeDepositNotification();
  void incorrectInputNotification();
  void successfulWithdrawNotification();
  void zeroWithdrawNotification();
  void insufficientFundsNotification();
  void negativeWithdrawNotification();
}
