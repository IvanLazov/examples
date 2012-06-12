package com.clouway.gwt.bank.client.account;

import com.clouway.gwt.bank.client.AccountServiceAsync;
import com.clouway.gwt.bank.client.SessionServiceAsync;
import com.clouway.gwt.bank.server.InstanceMatcher;
import com.clouway.gwt.bank.shared.exceptions.ExceededDepositException;
import com.clouway.gwt.bank.shared.exceptions.InvalidDepositException;
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
public class AccountDepositTest {

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
  public void depositHappyPath() {

    final double amount = 100;

    context.checking(new Expectations() {{
      oneOf(sessionService).isUserAuthorized(with(sessionCallback));

      oneOf(accountView).getEnteredAmount();
      will(returnValue(String.valueOf(amount)));

      oneOf(accountService).deposit(with(amount), with(accountCallback));

      oneOf(accountView).updatedBalanceNotification(String.valueOf(amount));
      oneOf(accountView).clearInputField();
      oneOf(accountView).successfulDepositNotification();
    }});

    accountPresenter.deposit();
    sessionCallback.getInstance().onSuccess(true);
    accountCallback.getInstance().onSuccess(amount);
  }

  @Test
  public void depositAmountLessThanOneNotAllowed() {

    pretendThatEnteredDepositAmountIs("0.01");
    accountPresenter.deposit();
    sessionCallback.getInstance().onSuccess(true);
  }

  @Test
  public void depositAmountGreaterThanTenThousandNotAllowed() {

    pretendThatEnteredDepositAmountIs("11000");
    accountPresenter.deposit();
    sessionCallback.getInstance().onSuccess(true);
  }

  @Test
  public void depositNotAllowedIfEnteredValueIsNotNumbers() {

    pretendThatEnteredDepositAmountIs("abc");
    accountPresenter.deposit();
    sessionCallback.getInstance().onSuccess(true);
  }

  private void pretendThatEnteredDepositAmountIs(final String amount) {
    context.checking(new Expectations() {{
      oneOf(sessionService).isUserAuthorized(with(sessionCallback));

      oneOf(accountView).getEnteredAmount();
      will(returnValue(amount));

      oneOf(accountView).invalidDepositNotification();
      oneOf(accountView).clearInputField();
    }});
  }

  @Test
  public void serverRespondWithExceededDepositExceptionWhenEnteredAmountIsGreaterThanTenThousand() {

    final double amount = 10000;

    context.checking(new Expectations() {{
      oneOf(sessionService).isUserAuthorized(with(sessionCallback));

      oneOf(accountView).getEnteredAmount();
      will(returnValue(String.valueOf(amount)));

      oneOf(accountService).deposit(with(amount), with(accountCallback));

      oneOf(accountView).invalidDepositNotification();
      oneOf(accountView).clearInputField();
    }});

    accountPresenter.deposit();
    sessionCallback.getInstance().onSuccess(true);
    accountCallback.getInstance().onFailure(new ExceededDepositException());
  }

  @Test
  public void serverRespondWithInvalidDepositExceptionWhenEnteredAmountIsLessThanOne() {

    final double amount = 100;

    context.checking(new Expectations() {{
      oneOf(sessionService).isUserAuthorized(with(sessionCallback));

      oneOf(accountView).getEnteredAmount();
      will(returnValue(String.valueOf(amount)));

      oneOf(accountService).deposit(with(amount), with(accountCallback));

      oneOf(accountView).invalidDepositNotification();
      oneOf(accountView).clearInputField();
    }});

    accountPresenter.deposit();
    sessionCallback.getInstance().onSuccess(true);
    accountCallback.getInstance().onFailure(new InvalidDepositException());
  }
}
