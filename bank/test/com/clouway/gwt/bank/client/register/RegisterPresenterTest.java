package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.BankServiceAsync;
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
  private BankServiceAsync fooRpcService = context.mock(BankServiceAsync.class);
  private RegisterView fooRegisterView = context.mock(RegisterView.class);
  private RegisterNotificationMessages fooNotification = context.mock(RegisterNotificationMessages.class);

  private InstanceMatcher<User> userInstanceMatcher = new InstanceMatcher<User>();
  private InstanceMatcher<AsyncCallback<Void>> asyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<Void>>();

  private final String usernameNotificationMessage = "Username must contain only letters and digits! Length (1-20) characters.";
  private final String passwordNotificationMessage = "Password must contain only letters and digits! Length (6-20) characters.";
  private final User user = new User("Test", "password");

  @Before
  public void setUp() {
    registerPresenter = new RegisterPresenter(fooRpcService, fooRegisterView, fooNotification);
  }

  @Test
  public void happyPath() {

    final String message = "Registration successful!";

    context.checking(new Expectations() {{
      oneOf(fooRegisterView).getUser();
      will(returnValue(user));

      oneOf(fooRpcService).registerUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(fooNotification).successfulRegistrationMessage();
      will(returnValue(message));

      oneOf(fooRegisterView).setNotification(message);
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

      oneOf(fooNotification).wrongUsernameMessage();
      will(returnValue(usernameNotificationMessage));

      oneOf(fooRegisterView).setNotification(usernameNotificationMessage);
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

      oneOf(fooNotification).wrongPasswordMessage();
      will(returnValue(passwordNotificationMessage));

      oneOf(fooRegisterView).setNotification(passwordNotificationMessage);
      oneOf(fooRegisterView).clearPasswordField();
    }});
  }

  @Test
  public void wrongUsernameExceptionIsCaught() {

    context.checking(new Expectations(){{
      oneOf(fooRegisterView).getUser();
      will(returnValue(user));

      oneOf(fooRpcService).registerUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(fooNotification).wrongUsernameMessage();
      will(returnValue(usernameNotificationMessage));

      oneOf(fooRegisterView).setNotification(usernameNotificationMessage);
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

      oneOf(fooNotification).wrongPasswordMessage();
      will(returnValue(passwordNotificationMessage));

      oneOf(fooRegisterView).setNotification(passwordNotificationMessage);
      oneOf(fooRegisterView).clearPasswordField();
    }});

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onFailure(new WrongPasswordException());
  }
}
