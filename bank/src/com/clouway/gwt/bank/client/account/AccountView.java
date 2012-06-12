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
  void updatedBalanceNotification(String amount);
  void successfulDepositNotification();
  void successfulWithdrawNotification();
  void invalidDepositNotification();
  void invalidWithdrawNotification();
  String getEnteredAmount();
}
