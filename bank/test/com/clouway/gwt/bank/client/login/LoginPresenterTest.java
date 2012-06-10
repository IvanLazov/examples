package com.clouway.gwt.bank.client.login;

import com.clouway.gwt.bank.client.AccountServiceAsync;
import com.clouway.gwt.bank.client.LoginServiceAsync;
import com.clouway.gwt.bank.client.presenter.Presenter;
import com.clouway.gwt.bank.shared.AuthorizedUser;
import com.clouway.gwt.bank.server.InstanceMatcher;
import com.clouway.gwt.bank.shared.User;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameOrPasswordException;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class LoginPresenterTest {

  private Mockery context = new JUnit4Mockery();

  private LoginServiceAsync rpcService = context.mock(LoginServiceAsync.class);
  private AccountServiceAsync accountRpcService = context.mock(AccountServiceAsync.class);
  private LoginView loginView = context.mock(LoginView.class);
  private Map<String, Presenter> userPages = new HashMap<String, Presenter>();

  private LoginPresenter loginPresenter = new LoginPresenter(userPages, accountRpcService, rpcService, loginView);

  private InstanceMatcher<User> userInstanceMatcher = new InstanceMatcher<User>();
  private InstanceMatcher<AsyncCallback<AuthorizedUser>> callbackInstanceMatcher = new InstanceMatcher<AsyncCallback<AuthorizedUser>>();

  @Test
  public void happyPath() {

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(new User("Test", "password")));

      oneOf(rpcService).loginUser(with(userInstanceMatcher), with(callbackInstanceMatcher));
    }});

    loginPresenter.loginUser();
  }

  @Test
  public void userCantLoginWithEmptyUsername() {

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(new User("", "password")));

      oneOf(loginView).wrongUsernameOrPasswordNotification();
    }});

    loginPresenter.loginUser();
  }

  @Test
  public void userCantLoginWithUsernameLongerThanTwentyCharacters() {

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(new User("TestTestTestTestTestTest", "password")));

      oneOf(loginView).wrongUsernameOrPasswordNotification();
    }});

    loginPresenter.loginUser();
  }

  @Test
  public void userCantLoginWithUsernameContainingSpecialCharacters() {

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(new User("Test!@#$", "password")));

      oneOf(loginView).wrongUsernameOrPasswordNotification();
    }});

    loginPresenter.loginUser();
  }

  @Test
  public void userCantLoginWithPasswordLessThanSixCharacters() {

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(new User("Test", "pass")));

      oneOf(loginView).wrongUsernameOrPasswordNotification();
    }});

    loginPresenter.loginUser();
  }

  @Test
  public void userCantLoginWithPasswordLongerThanTwentyCharacters() {

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(new User("Test", "passwordpasswordpassword")));

      oneOf(loginView).wrongUsernameOrPasswordNotification();
    }});

    loginPresenter.loginUser();
  }

  @Test
  public void userCantLoginWithEmptyPassword() {

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(new User("Test", "")));

      oneOf(loginView).wrongUsernameOrPasswordNotification();
    }});

    loginPresenter.loginUser();
  }

  @Test
  public void userCantLoginWithEmptyUsernameAndPassword() {

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(new User("", "")));

      oneOf(loginView).wrongUsernameOrPasswordNotification();
    }});

    loginPresenter.loginUser();
  }

  @Test
  public void exceptionIsThrownForWrongUsernameOrPassword() {

    final User user = new User("Test", "password");

    context.checking(new Expectations(){{
      oneOf(loginView).getUser();
      will(returnValue(user));

      oneOf(rpcService).loginUser(with(userInstanceMatcher), with(callbackInstanceMatcher));

      oneOf(loginView).wrongUsernameOrPasswordNotification();
    }});

    loginPresenter.loginUser();
    callbackInstanceMatcher.getInstance().onFailure(new WrongUsernameOrPasswordException());
  }
}
