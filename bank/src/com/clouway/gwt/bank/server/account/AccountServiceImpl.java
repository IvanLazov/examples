package com.clouway.gwt.bank.server.account;

import com.clouway.gwt.bank.client.AccountService;
import com.clouway.gwt.bank.server.ExecuteInTransaction;
import com.clouway.gwt.bank.server.session.SessionRepository;
import com.clouway.gwt.bank.shared.AuthorizedUser;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.lang.reflect.Method;

@Singleton
public class AccountServiceImpl extends XsrfProtectedServiceServlet implements AccountService {

  private final AccountRepository accountRepository;
  private final SessionRepository sessionRepository;
  private XsrfToken token;

  @Inject
  public AccountServiceImpl(AccountRepository accountRepository, SessionRepository sessionRepository) {
    this.accountRepository = accountRepository;
    this.sessionRepository = sessionRepository;
  }

  @Override
  protected void validateXsrfToken(RpcToken token, Method method) throws RpcTokenException {
    super.validateXsrfToken(token, method);
    this.token = (XsrfToken) token;
  }

  @ExecuteInTransaction
  public double deposit(double amount) {

    AuthorizedUser user = sessionRepository.getUser(token.getToken());
    Account account = accountRepository.getAccount(user.getUserId());
    account.deposit(amount);
    accountRepository.updateAccount(user.getUserId(), account.getBalance());

    return account.getBalance();
  }

  @ExecuteInTransaction
  public double withdraw(double amount) {

    AuthorizedUser user = sessionRepository.getUser(token.getToken());
    Account account = accountRepository.getAccount(user.getUserId());
    account.withdraw(amount);
    accountRepository.updateAccount(user.getUserId(), account.getBalance());

    return account.getBalance();
  }

  public double getBalance() {

    AuthorizedUser user = sessionRepository.getUser(token.getToken());
    Account account = accountRepository.getAccount(user.getUserId());
    return account.getBalance();
  }
}