package com.clouway.gwt.bank.client.account;

import com.clouway.gwt.bank.client.BankServiceAsync;
import com.clouway.gwt.bank.client.exceptions.InsufficientFundsException;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountPresenter implements Presenter, AccountView.Presenter {

  private final BankServiceAsync rpcService;
  private final AccountView view;

  public AccountPresenter(BankServiceAsync rpcService, AccountView view) {
    this.rpcService = rpcService;
    this.view = view;
  }

  public void go(HasWidgets container) {
    container.add((Widget) view);
    this.view.setPresenter(this);
  }

  public void deposit() {
    double amount = 0;

    try {
      amount = Double.parseDouble(view.getEnteredAmount());
    } catch (NumberFormatException exception) {
      view.invalidInput();
      view.clearInputField();
      return;
    }

    if (amount == 0) {
      view.zeroAmountDeposit();
      view.clearInputField();
      return;
    }

    if (amount > 10000) {
      view.exceededDeposit();
      view.clearInputField();
      return;
    }

    if (amount < 0) {
      view.negativeDeposit();
      view.clearInputField();
      return;
    }

    rpcService.deposit(amount, new AsyncCallback<Double>() {
      public void onFailure(Throwable caught) {

      }

      public void onSuccess(Double result) {
        view.updateBalance(String.valueOf(result));
        view.clearInputField();
        view.successfulDeposit();
      }
    });
  }

  public void withdraw() {

    double amount = 0;

    try {
      amount = Double.parseDouble(view.getEnteredAmount());
    } catch (NumberFormatException exception) {
      view.clearInputField();
      view.invalidInput();
      return;
    }

    if (amount == 0) {
      view.clearInputField();
      view.zeroAmountWithdraw();
      return;
    }

    if (amount < 0) {
      view.clearInputField();
      view.negativeWithdraw();
      return;
    }

    rpcService.withdraw(amount, new AsyncCallback<Double>() {
      public void onFailure(Throwable caught) {
        if (caught instanceof InsufficientFundsException) {
          view.clearInputField();
          view.insufficientFunds();
        }
      }

      public void onSuccess(Double result) {
        System.out.println(result);
        view.updateBalance(String.valueOf(result));
        view.clearInputField();
        view.successfulWithdraw();
      }
    });
  }
}
