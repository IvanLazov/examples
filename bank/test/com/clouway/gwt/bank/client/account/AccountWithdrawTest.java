package com.clouway.gwt.bank.client.account;

import com.clouway.gwt.bank.client.AccountServiceAsync;
import com.clouway.gwt.bank.client.SessionServiceAsync;
import com.clouway.gwt.bank.server.InstanceMatcher;
import com.clouway.gwt.bank.shared.exceptions.InsufficientFundsException;
import com.clouway.gwt.bank.shared.exceptions.InvalidWithdrawException;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class AccountWithdrawTest {

  private Mockery context = new JUnit4Mockery();

  private AccountView accountView = context.mock(AccountView.class);
  private AccountServiceAsync accountService = context.mock(AccountServiceAsync.class);
  private SessionServiceAsync sessionService = context.mock(SessionServiceAsync.class);

  private InstanceMatcher<AsyncCallback<Boolean>> sessionCallback = new InstanceMatcher<AsyncCallback<Boolean>>();
  private InstanceMatcher<AsyncCallback<Double>> accountCallback = new InstanceMatcher<AsyncCallback<Double>>();

  private AccountPresenter accountPresenter;

  @Before
  public void setUp() {
    accountPresenter = new AccountPresenter(accountService, sessionService, accountView);
  }

  @Test
  public void withdrawHappyPath() {

    final double amount = 100;

    context.checking(new Expectations(){{
      oneOf(sessionService).isUserAuthorized(with(sessionCallback));

      oneOf(accountView).getEnteredAmount();
      will(returnValue(String.valueOf(amount)));

      oneOf(accountService).withdraw(with(amount), with(accountCallback));

      oneOf(accountView).updatedBalanceNotification(String.valueOf(amount));
      oneOf(accountView).clearInputField();
      oneOf(accountView).successfulWithdrawNotification();
    }});

    accountPresenter.withdraw();
    sessionCallback.getInstance().onSuccess(true);
    accountCallback.getInstance().onSuccess(amount);
  }

  private void pretendThatEnteredWithdrawAmountIs(final String amount) {

    context.checking(new Expectations(){{
      oneOf(sessionService).isUserAuthorized(with(sessionCallback));

      oneOf(accountView).getEnteredAmount();
      will(returnValue(amount));

      oneOf(accountView).invalidWithdrawNotification();
      oneOf(accountView).clearInputField();
    }});
  }

  @Test
  public void withdrawAmountLessThanOneNotAllowed() {

    pretendThatEnteredWithdrawAmountIs("0.01");
    accountPresenter.withdraw();
    sessionCallback.getInstance().onSuccess(true);
  }

  @Test
  public void withdrawNotAllowedWhenEnteredValueIsNotNumbers() {

    pretendThatEnteredWithdrawAmountIs("abc");
    accountPresenter.withdraw();
    sessionCallback.getInstance().onSuccess(true);
  }

  @Test
  public void serverRespondWithInsufficientFundsExceptionWhenEnteredAmountIsGreaterThanAccountBalance() {

    final double amount = 100;

    context.checking(new Expectations(){{
      oneOf(sessionService).isUserAuthorized(with(sessionCallback));

      oneOf(accountView).getEnteredAmount();
      will(returnValue(String.valueOf(amount)));

      oneOf(accountService).withdraw(with(amount), with(accountCallback));

      oneOf(accountView).invalidWithdrawNotification();
      oneOf(accountView).clearInputField();
    }});

    accountPresenter.withdraw();
    sessionCallback.getInstance().onSuccess(true);
    accountCallback.getInstance().onFailure(new InsufficientFundsException());
  }

  @Test
  public void serverRespondWithInvalidWithdrawExceptionWhenEnteredAmountIsLessThanOne() {

    final double amount = 100;

    context.checking(new Expectations(){{
      oneOf(sessionService).isUserAuthorized(with(sessionCallback));

      oneOf(accountView).getEnteredAmount();
      will(returnValue(String.valueOf(amount)));

      oneOf(accountService).withdraw(with(amount), with(accountCallback));

      oneOf(accountView).invalidWithdrawNotification();
      oneOf(accountView).clearInputField();
    }});

    accountPresenter.withdraw();
    sessionCallback.getInstance().onSuccess(true);
    accountCallback.getInstance().onFailure(new InvalidWithdrawException());
  }
}
