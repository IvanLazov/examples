package com.clouway.gwt.bank.client.account;

import com.clouway.gwt.bank.client.AccountServiceAsync;
import com.clouway.gwt.bank.shared.exceptions.ExceededDepositException;
import com.clouway.gwt.bank.shared.exceptions.InsufficientFundsException;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountPresenter implements Presenter, AccountView.Presenter {

  private final AccountServiceAsync rpcService;
  private final AccountView view;

  public AccountPresenter(AccountServiceAsync rpcService, AccountView view) {
    this.rpcService = rpcService;
    this.view = view;
  }

  public void go(HasWidgets container) {
    container.add((Widget) view);
    this.view.setPresenter(this);
  }

  public void deposit() {
    double amount;

    try {
      amount = Double.parseDouble(view.getEnteredAmount());
    } catch (NumberFormatException exception) {
      view.incorrectInputNotification();
      view.clearInputField();
      return;
    }

    if (amount == 0) {
      view.zeroDepositNotification();
      view.clearInputField();
      return;
    }

    if (amount > 10000) {
      view.exceededDepositNotification();
      view.clearInputField();
      return;
    }

    if (amount < 0) {
      view.negativeDepositNotification();
      view.clearInputField();
      return;
    }

    rpcService.deposit(amount, new AsyncCallback<Double>() {
      public void onFailure(Throwable caught) {

      }

      public void onSuccess(Double result) {
        view.updatedBalanceNotification(String.valueOf(result));
        view.clearInputField();
        view.successfulDepositNotification();
      }
    });
  }

  public void withdraw() {

    double amount;

    try {
      amount = Double.parseDouble(view.getEnteredAmount());
    } catch (NumberFormatException exception) {
      view.clearInputField();
      view.incorrectInputNotification();
      return;
    }

    if (amount == 0) {
      view.clearInputField();
      view.zeroWithdrawNotification();
      return;
    }

    if (amount < 0) {
      view.clearInputField();
      view.negativeWithdrawNotification();
      return;
    }

    rpcService.withdraw(amount, new AsyncCallback<Double>() {
      public void onFailure(Throwable caught) {
        if (caught instanceof InsufficientFundsException) {
          view.clearInputField();
          view.insufficientFundsNotification();
        }

        if (caught instanceof ExceededDepositException) {
          view.clearInputField();
          view.exceededDepositNotification();
        }
      }

      public void onSuccess(Double result) {
        view.updatedBalanceNotification(String.valueOf(result));
        view.clearInputField();
        view.successfulWithdrawNotification();
      }
    });
  }

  public void logoutUser() {
    rpcService.logoutUser(new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {

      }

      public void onSuccess(Void result) {
        History.newItem("login");
      }
    });
  }
}
