package com.clouway.gwt.bank.client.login;

import com.clouway.gwt.bank.client.UserServiceAsync;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameOrPasswordException;
import com.clouway.gwt.bank.InstanceMatcher;
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

  private UserServiceAsync rpcService = context.mock(UserServiceAsync.class);
  private LoginView loginView = context.mock(LoginView.class);
  private LoginPresenter loginPresenter = new LoginPresenter(rpcService, loginView);

  private InstanceMatcher<User> userInstanceMatcher = new InstanceMatcher<User>();
  private InstanceMatcher<AsyncCallback<User>> asyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<User>>();

  @Test
  public void happyPath() {

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(new User("Test", "password")));

      oneOf(rpcService).loginUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));
    }});

    loginPresenter.loginUser();
  }

  @Test
  public void asyncCallbackOnFailure() {

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(new User("Test", "password")));

      oneOf(rpcService).loginUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(loginView).showWrongUsernameOrPasswordNotification();
    }});

    loginPresenter.loginUser();
    asyncCallbackInstanceMatcher.getInstance().onFailure(new WrongUsernameOrPasswordException());
  }

  @Test
  public void enteredWrongUsername() {

    enteredWrongUsernameOrPassword(new User("Test!@#", "password"));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredUsernameLongerThanTwentyCharacters() {

    enteredWrongUsernameOrPassword(new User("TestTestTestTestTestTest", "password"));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredEmptyUsername() {

    enteredWrongUsernameOrPassword(new User("", "password"));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredPasswordShortThanSixCharacters() {

    enteredWrongUsernameOrPassword(new User("Test", "pass"));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredPasswordLongerThanTwentyCharacters() {

    enteredWrongUsernameOrPassword(new User("Test", "passwordpasswordpassword"));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredEmptyPassword() {

    enteredWrongUsernameOrPassword(new User("Test", ""));
    loginPresenter.loginUser();
  }

  @Test
  public void enteredEmptyUsernameAndPassword() {

    enteredWrongUsernameOrPassword(new User("", ""));
    loginPresenter.loginUser();
  }

  private void enteredWrongUsernameOrPassword(final User user) {
    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(user));

      oneOf(loginView).showWrongUsernameOrPasswordNotification();
    }});
  }
}
