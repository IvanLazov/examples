package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.BankServiceAsync;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameOrPasswordException;
import com.clouway.gwt.bank.client.login.LoginPresenter;
import com.clouway.gwt.bank.client.login.LoginView;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class LoginPresenterTest {

  private Mockery context = new JUnit4Mockery();

  private BankServiceAsync fooRpcService = context.mock(BankServiceAsync.class);
  private LoginView fooLoginView = context.mock(LoginView.class);
  private LoginPresenter loginPresenter = new LoginPresenter(fooRpcService, fooLoginView);

  private InstanceMatcher<User> userInstanceMatcher = new InstanceMatcher<User>();
  private InstanceMatcher<AsyncCallback<User>> asyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<User>>();

  @Test
  public void happyPath() {

    context.checking(new Expectations(){{
      oneOf(fooLoginView).getUser();
      will(returnValue(new User("Test", "password")));

      oneOf(fooRpcService).loginUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));
    }});

    loginPresenter.loginUser();
  }

  @Test
  public void asyncCallbackOnFailure() {

    context.checking(new Expectations(){{
      oneOf(fooLoginView).getUser();
      will(returnValue(new User("Test", "password")));

      oneOf(fooRpcService).loginUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(fooLoginView).showWrongUsernameOrPasswordNotification();
    }});

    loginPresenter.loginUser();
    asyncCallbackInstanceMatcher.getInstance().onFailure(new WrongUsernameOrPasswordException());
  }

  @Test
  public void enteredWrongUsername() {

    pretendThatEnteredUserIs(new User("Test!@#", "password"));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredUsernameLongerThanTwentyCharacters() {

    pretendThatEnteredUserIs(new User("TestTestTestTestTestTest", "password"));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredEmptyUsername() {

    pretendThatEnteredUserIs(new User("", "password"));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredPasswordShortThanSixCharacters() {

    pretendThatEnteredUserIs(new User("Test", "pass"));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredPasswordLongerThanTwentyCharacters() {

    pretendThatEnteredUserIs(new User("Test", "passwordpasswordpassword"));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredEmptyPassword() {

    pretendThatEnteredUserIs(new User("Test", ""));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredEmptyUsernameAndPassword() {

    pretendThatEnteredUserIs(new User("", ""));
    loginPresenter.loginUser();
  }

  private void pretendThatEnteredUserIs(final User user) {
    context.checking(new Expectations(){{
      oneOf(fooLoginView).getUser();
      will(returnValue(user));

      oneOf(fooLoginView).showWrongUsernameOrPasswordNotification();
    }});
  }
}
