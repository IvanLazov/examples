package com.clouway.gwt.bank.client.account;

import com.clouway.gwt.bank.client.AccountServiceAsync;
import com.clouway.gwt.bank.client.SessionServiceAsync;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.shared.exceptions.ExceededDepositException;
import com.clouway.gwt.bank.shared.exceptions.InsufficientFundsException;
import com.clouway.gwt.bank.shared.exceptions.InvalidDepositException;
import com.clouway.gwt.bank.shared.exceptions.InvalidWithdrawException;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountPresenter implements Presenter, AccountView.Presenter {

  private final AccountServiceAsync accountService;
  private final SessionServiceAsync sessionService;
  private final AccountView accountView;

  public AccountPresenter(AccountServiceAsync accountService, SessionServiceAsync sessionService, AccountView accountView) {
    this.accountService = accountService;
    this.sessionService = sessionService;
    this.accountView = accountView;
  }

  public void go(HasWidgets container) {
    container.add((Widget) accountView);
    this.accountView.setPresenter(this);
    getAccountBalance();
  }

  /**
   * Get user's account balance
   */
  private void getAccountBalance() {

    accountService.getBalance(new AsyncCallback<Double>() {
      public void onFailure(Throwable caught) {
      }

      public void onSuccess(Double result) {
        accountView.updatedBalanceNotification(result.toString());
      }
    });
  }

  /**
   * Deposit amount to user's account
   */
  public void deposit() {

    sessionService.isUserAuthorized(new AsyncCallback<Boolean>() {
      public void onFailure(Throwable caught) {
        History.newItem("login");
      }

      public void onSuccess(Boolean result) {
        double amount;

        try {
          amount = Double.parseDouble(accountView.getEnteredAmount());
        } catch (NumberFormatException exception) {
          accountView.invalidDepositNotification();
          accountView.clearInputField();
          return;
        }

        if (amount < 1 || amount > 10000) {
          accountView.invalidDepositNotification();
          accountView.clearInputField();
          return;
        }

        accountService.deposit(amount, new AsyncCallback<Double>() {
          public void onFailure(Throwable caught) {
            if ((caught instanceof ExceededDepositException) || (caught instanceof InvalidDepositException)) {
              accountView.invalidDepositNotification();
              accountView.clearInputField();
            }
          }

          public void onSuccess(Double result) {
            accountView.updatedBalanceNotification(String.valueOf(result));
            accountView.clearInputField();
            accountView.successfulDepositNotification();
          }
        });
      }
    });
  }

  /**
   * Withdraw amount from user's account
   */
  public void withdraw() {

    sessionService.isUserAuthorized(new AsyncCallback<Boolean>() {
      public void onFailure(Throwable caught) {
        History.newItem("login");
      }

      public void onSuccess(Boolean result) {
        double amount;

        try {
          amount = Double.parseDouble(accountView.getEnteredAmount());
        } catch (NumberFormatException exception) {
          accountView.invalidWithdrawNotification();
          accountView.clearInputField();
          return;
        }

        if (amount < 1) {
          accountView.invalidWithdrawNotification();
          accountView.clearInputField();
          return;
        }

        accountService.withdraw(amount, new AsyncCallback<Double>() {
          public void onFailure(Throwable caught) {
            if ((caught instanceof InsufficientFundsException) || (caught instanceof InvalidWithdrawException)) {
              accountView.invalidWithdrawNotification();
              accountView.clearInputField();
            }
          }

          public void onSuccess(Double result) {
            accountView.updatedBalanceNotification(String.valueOf(result));
            accountView.clearInputField();
            accountView.successfulWithdrawNotification();
          }
        });
      }
    });
  }

  /**
   * Logout user
   */
  public void logoutUser() {
    sessionService.logoutUser(new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {
      }

      public void onSuccess(Void result) {
        History.newItem("login");
      }
    });
  }
}
