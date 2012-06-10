package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.server.InstanceMatcher;
import com.clouway.gwt.bank.client.RegisterServiceAsync;
import com.clouway.gwt.bank.shared.exceptions.UsernameAlreadyTakenException;
import com.clouway.gwt.bank.shared.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameException;
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

  private RegisterPresenter presenter;
  private User user = new User("Test", "password");

  private RegisterServiceAsync rpcService = context.mock(RegisterServiceAsync.class);
  private RegisterView view = context.mock(RegisterView.class);

  private InstanceMatcher<User> userInstanceMatcher = new InstanceMatcher<User>();
  private InstanceMatcher<AsyncCallback<Void>> callbackInstanceMatcher = new InstanceMatcher<AsyncCallback<Void>>();

  @Before
  public void setUp() {
    presenter = new RegisterPresenter(rpcService, view);
  }

  @Test
  public void happyPath() {

    context.checking(new Expectations(){{
      oneOf(view).getUser();
      will(returnValue(new User("Test", "password")));

      oneOf(rpcService).registerUser(with(userInstanceMatcher), with(callbackInstanceMatcher));

      oneOf(view).registrationSuccessfulNotification();
      oneOf(view).clearUsernameField();
      oneOf(view).clearPasswordField();
    }});

    presenter.registerUser();
    callbackInstanceMatcher.getInstance().onSuccess(null);
  }

  @Test
  public void userCantRegisterWithEmptyUsername() {

    pretendThatEnteredUsernameIs("");
    presenter.registerUser();
  }

  @Test
  public void userCantRegisterWithEmptyPassword() {

    pretendThatEnteredPasswordIs("");
    presenter.registerUser();
  }

  @Test
  public void userCantRegisterWithUsernameLongerThanTwentyCharacters() {

    pretendThatEnteredUsernameIs("TestTestTestTestTestTest");
    presenter.registerUser();
  }

  @Test
  public void userCantRegisterWithPasswordLessThanSixCharacters() {

    pretendThatEnteredPasswordIs("pass");
    presenter.registerUser();
  }

  @Test
  public void userCantRegisterWithPasswordLongerThanTwentyCharacters() {

    pretendThatEnteredPasswordIs("passwordpasswordpassword");
    presenter.registerUser();
  }

  @Test
  public void userCantRegisterWithUsernameContainingSpecialCharacters() {

    pretendThatEnteredUsernameIs("Test!@#$");
    presenter.registerUser();
  }

  @Test
  public void userCantRegisterWithPasswordContainingSpecialCharacters() {

    pretendThatEnteredPasswordIs("pass!@#$");
    presenter.registerUser();
  }

  @Test
  public void usernameAlreadyTaken() {

    context.checking(new Expectations(){{
      oneOf(view).getUser();
      will(returnValue(user));

      oneOf(rpcService).registerUser(with(user), with(callbackInstanceMatcher));

      oneOf(view).usernameAlreadyTakenNotification();
      oneOf(view).clearUsernameField();
      oneOf(view).clearPasswordField();
    }});

    presenter.registerUser();
    callbackInstanceMatcher.getInstance().onFailure(new UsernameAlreadyTakenException());
  }

  @Test
  public void exceptionIsThrownForWrongUsername() {

    context.checking(new Expectations(){{
      oneOf(view).getUser();
      will(returnValue(user));

      oneOf(rpcService).registerUser(with(userInstanceMatcher), with(callbackInstanceMatcher));

      oneOf(view).wrongUsernameNotification();
      oneOf(view).clearPasswordField();
    }});

    presenter.registerUser();
    callbackInstanceMatcher.getInstance().onFailure(new WrongUsernameException());
  }

  @Test
  public void exceptionIsThrownForWrongPassword() {

    context.checking(new Expectations(){{
      oneOf(view).getUser();
      will(returnValue(user));

      oneOf(rpcService).registerUser(with(userInstanceMatcher), with(callbackInstanceMatcher));

      oneOf(view).wrongPasswordNotification();
      oneOf(view).clearPasswordField();
    }});

    presenter.registerUser();
    callbackInstanceMatcher.getInstance().onFailure(new WrongPasswordException());
  }

  private void pretendThatEnteredUsernameIs(final String username) {
    context.checking(new Expectations(){{
      oneOf(view).getUser();
      will(returnValue(new User(username, "password")));

      oneOf(view).wrongUsernameNotification();
      oneOf(view).clearPasswordField();
    }});
  }

  private void pretendThatEnteredPasswordIs(final String password) {
    context.checking(new Expectations(){{
      oneOf(view).getUser();
      will(returnValue(new User("Test", password)));

      oneOf(view).wrongPasswordNotification();
      oneOf(view).clearPasswordField();
    }});
  }
}
