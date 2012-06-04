package com.clouway.gwt.bank.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BankServiceAsync {

  void deposit(double amount, AsyncCallback<Double> async);

  void withdraw(double amount, AsyncCallback<Double> async);
}
