package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.shared.exceptions.ExceededDepositException;
import com.clouway.gwt.bank.shared.exceptions.InsufficientFundsException;
import com.clouway.gwt.bank.shared.exceptions.InvalidDepositException;
import com.clouway.gwt.bank.shared.exceptions.InvalidWithdrawException;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;

@RemoteServiceRelativePath("accountService")
public interface AccountService extends XsrfProtectedService {

  double deposit(double amount) throws ExceededDepositException, InvalidDepositException;
  double withdraw(double amount) throws InsufficientFundsException, InvalidWithdrawException;
  void logoutUser();
}
