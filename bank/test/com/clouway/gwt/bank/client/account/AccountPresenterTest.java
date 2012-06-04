package com.clouway.gwt.bank.client.account;

import com.clouway.gwt.bank.client.BankServiceAsync;
import com.clouway.gwt.bank.client.exceptions.InsufficientFundsException;
import com.clouway.gwt.bank.InstanceMatcher;
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
public class AccountPresenterTest {

  Mockery context = new JUnit4Mockery();
  AccountView accountView = context.mock(AccountView.class);
  BankServiceAsync rpcService = context.mock(BankServiceAsync.class);

  AccountPresenter accountPresenter;

  InstanceMatcher<Double> amountInstanceMatcher = new InstanceMatcher<Double>();
  InstanceMatcher<AsyncCallback<Double>> callbackInstanceMatcher = new InstanceMatcher<AsyncCallback<Double>>();

  @Before
  public void setUp() {
    this.accountPresenter = new AccountPresenter(rpcService, accountView);
  }

  @Test
  public void depositPositiveAmount() {

    final double returnedAmount = 1100;

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue("100.0"));

      oneOf(rpcService).deposit(with(amountInstanceMatcher), with(callbackInstanceMatcher));

      oneOf(accountView).updateBalance(String.valueOf(returnedAmount));

      oneOf(accountView).clearInputField();

      oneOf(accountView).successfulDeposit();
    }});

    accountPresenter.deposit();
    callbackInstanceMatcher.getInstance().onSuccess(returnedAmount);
  }

  @Test
  public void depositZeroAmountNotAllowed() {

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue("0.0"));

      oneOf(accountView).zeroAmountDeposit();

      oneOf(accountView).clearInputField();
    }});

    accountPresenter.deposit();
  }

  @Test
  public void depositAmountBiggerThanTenThousandNotAllowed() {

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue("11000.0"));

      oneOf(accountView).exceededDeposit();

      oneOf(accountView).clearInputField();
    }});

    accountPresenter.deposit();
  }

  @Test
  public void depositNotExecutedWhenEnteredAmountIsLetters() {

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue("ABC"));

      oneOf(accountView).invalidInput();

      oneOf(accountView).clearInputField();
    }});

    accountPresenter.deposit();
  }

  @Test
  public void depositNotExecutedWhenEnteredEmptyAmount() {

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue(""));

      oneOf(accountView).invalidInput();
      oneOf(accountView).clearInputField();
    }});

    accountPresenter.deposit();
  }

  @Test
  public void depositNegativeAmountNotAllowed() {

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue("-100.0"));

      oneOf(accountView).negativeDeposit();

      oneOf(accountView).clearInputField();
    }});

    accountPresenter.deposit();
  }

  @Test
  public void withdrawPositiveAmount() {

    final double returnedAmount = 500;

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue("100.0"));

      oneOf(rpcService).withdraw(with(amountInstanceMatcher), with(callbackInstanceMatcher));

      oneOf(accountView).updateBalance(String.valueOf(returnedAmount));
      oneOf(accountView).successfulWithdraw();
      oneOf(accountView).clearInputField();
    }});

    accountPresenter.withdraw();
    callbackInstanceMatcher.getInstance().onSuccess(returnedAmount);
  }

  @Test
  public void withdrawZeroAmountNotExecuted() {

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue("0.0"));

      oneOf(accountView).clearInputField();
      oneOf(accountView).zeroAmountWithdraw();
    }});

    accountPresenter.withdraw();
  }

  @Test
  public void cannotWithdrawAmountThatExceedsCurrentBalance() {

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue("500.0"));

      oneOf(rpcService).withdraw(with(amountInstanceMatcher), with(callbackInstanceMatcher));

      oneOf(accountView).clearInputField();
      oneOf(accountView).insufficientFunds();
    }});

    accountPresenter.withdraw();
    callbackInstanceMatcher.getInstance().onFailure(new InsufficientFundsException());
  }

  @Test
  public void withdrawNotExecutedWhenEnteredEmptyAmount() {

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue(""));

      oneOf(accountView).clearInputField();
      oneOf(accountView).invalidInput();
    }});

    accountPresenter.withdraw();
  }

  @Test
  public void negativeAmountNotAllowedToWithdraw() {

    context.checking(new Expectations(){{
      oneOf(accountView).getEnteredAmount();
      will(returnValue("-100.0"));

      oneOf(accountView).clearInputField();
      oneOf(accountView).negativeWithdraw();
    }});

    accountPresenter.withdraw();
  }
}
