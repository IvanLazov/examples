package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.BankServiceAsync;
import com.clouway.gwt.bank.client.register.exceptions.InvalidPasswordException;
import com.clouway.gwt.bank.client.register.exceptions.InvalidUsernameException;
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
  private BankServiceAsync mockRpcService = context.mock(BankServiceAsync.class);
  private RegisterView mockRegisterView = context.mock(RegisterView.class);

  private InstanceMatcher<User> userInstanceMatcher = new InstanceMatcher<User>();
  private InstanceMatcher<AsyncCallback<Void>> asyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<Void>>();

  @Before
  public void setUp() {
    registerPresenter = new RegisterPresenter(mockRpcService, mockRegisterView);
  }

  @Test
  public void newUserRegistration() {

    context.checking(new Expectations() {{
      oneOf(mockRegisterView).getUser();
      will(returnValue(new User("Test", "password")));

      oneOf(mockRpcService).registerUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(mockRegisterView).clearFields();
      oneOf(mockRegisterView).setNotification(with("Registration was successful!"));
    }});

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onSuccess(null);
  }

  @Test(expected = InvalidUsernameException.class)
  public void usernameIsEmpty() {

    mockedViewReturnsUser(new User("", "password"));
    registerPresenter.registerUser();
  }

  @Test(expected = InvalidUsernameException.class)
  public void usernameIsTooLong() {

    mockedViewReturnsUser(new User("TestTestTestTestTestTest", "password"));
    registerPresenter.registerUser();
  }

  @Test(expected = InvalidUsernameException.class)
  public void usernameContainsForbiddenCharacters() {

    mockedViewReturnsUser(new User("Test@#$", "password"));
    registerPresenter.registerUser();
  }

  @Test(expected = InvalidPasswordException.class)
  public void passwordIsEmtpy() {

    mockedViewReturnsUser(new User("Test", ""));
    registerPresenter.registerUser();
  }

  @Test(expected = InvalidPasswordException.class)
  public void passwordIsTooShort() {

    mockedViewReturnsUser(new User("Test", "pass"));
    registerPresenter.registerUser();
  }

  @Test(expected = InvalidPasswordException.class)
  public void passwordIsTooLong() {

    mockedViewReturnsUser(new User("Test", "passwordpasswordpassword"));
    registerPresenter.registerUser();
  }

  @Test(expected = InvalidPasswordException.class)
  public void passwordContainsForbiddenCharacters() {

    mockedViewReturnsUser(new User("Test", "password@#$%"));
    registerPresenter.registerUser();
  }

  private void mockedViewReturnsUser(final User user) {

    context.checking(new Expectations() {{
      oneOf(mockRegisterView).getUser();
      will(returnValue(user));
    }});
  }
}
