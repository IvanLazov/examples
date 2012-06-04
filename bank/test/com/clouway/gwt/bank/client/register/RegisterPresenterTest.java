package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.InstanceMatcher;
import com.clouway.gwt.bank.client.UserServiceAsync;
import com.clouway.gwt.bank.client.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.client.exceptions.WrongUsernameException;
import com.clouway.gwt.bank.shared.User;
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
public class RegisterPresenterTest {

  private Mockery context = new JUnit4Mockery();

  private RegisterPresenter registerPresenter;
  private UserServiceAsync fooRpcService = context.mock(UserServiceAsync.class);
  private RegisterView fooRegisterView = context.mock(RegisterView.class);

  private InstanceMatcher<User> userInstanceMatcher = new InstanceMatcher<User>();
  private InstanceMatcher<AsyncCallback<Void>> asyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<Void>>();

  private final User user = new User("Test", "password");

  @Before
  public void setUp() {
    registerPresenter = new RegisterPresenter(fooRpcService, fooRegisterView);
  }

  @Test
  public void happyPath() {

    context.checking(new Expectations() {{
      oneOf(fooRegisterView).getUser();
      will(returnValue(user));

      oneOf(fooRpcService).registerUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(fooRegisterView).showSuccessfulRegistrationNotification();
      oneOf(fooRegisterView).clearUsernameField();
      oneOf(fooRegisterView).clearPasswordField();
    }});

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onSuccess(null);
  }

  @Test
  public void userCannotRegisterWithEmptyUsername() {

    pretendThatEnteredUsernameIs("");
    registerPresenter.registerUser();
  }

  @Test
  public void userCannotRegisterWithUsernameLongerThanTwentyCharacters() {

    pretendThatEnteredUsernameIs("UserCannotRegisterWithTooLongUsername");
    registerPresenter.registerUser();
  }

  private void pretendThatEnteredUsernameIs(final String username) {
    context.checking(new Expectations(){{
      oneOf(fooRegisterView).getUser();
      will(returnValue(new User(username,  "password")));

      oneOf(fooRegisterView).showWrongUsernameNotification();
      oneOf(fooRegisterView).clearPasswordField();
    }});
  }

  @Test
  public void userCannotRegisterWithEmptyPassword() {

    pretendThatEnteredPasswordIs("");
    registerPresenter.registerUser();
  }

  @Test
  public void userCannotRegisterWithPasswordLessThanSixCharacters() {

    pretendThatEnteredPasswordIs("pass");
    registerPresenter.registerUser();
  }

  @Test
  public void userCannotRegisterWithPasswordLongerThanTwentyCharacters() {

    pretendThatEnteredPasswordIs("passwordPasswordPassword");
    registerPresenter.registerUser();
  }

  private void pretendThatEnteredPasswordIs(final String password) {
    context.checking(new Expectations(){{
      oneOf(fooRegisterView).getUser();
      will(returnValue(new User("Test", password)));

      oneOf(fooRegisterView).showWrongPasswordNotification();
      oneOf(fooRegisterView).clearPasswordField();
    }});
  }

  @Test
  public void wrongUsernameExceptionIsCaught() {

    context.checking(new Expectations(){{
      oneOf(fooRegisterView).getUser();
      will(returnValue(user));

      oneOf(fooRpcService).registerUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(fooRegisterView).showWrongUsernameNotification();
      oneOf(fooRegisterView).clearPasswordField();
    }});

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onFailure(new WrongUsernameException());
  }

  @Test
  public void wrongPasswordExceptionIsCaught() {

    context.checking(new Expectations(){{
      oneOf(fooRegisterView).getUser();
      will(returnValue(user));

      oneOf(fooRpcService).registerUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(fooRegisterView).showWrongPasswordNotification();
      oneOf(fooRegisterView).clearPasswordField();
    }});

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onFailure(new WrongPasswordException());
  }
}
