package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.BankServiceAsync;
import com.clouway.gwt.bank.client.register.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.client.register.exceptions.WrongUsernameException;
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

  private InstanceMatcher<User> userInstanceMatcher = new InstanceMatcher<User>();
  private InstanceMatcher<AsyncCallback<Void>> asyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<Void>>();
  private InstanceMatcher<String> notificationMessage = new InstanceMatcher<String>();

  @Before
  public void setUp() {
    registerPresenter = new RegisterPresenter(fooRpcService, fooRegisterView);
  }

  @Test
  public void newUserRegistration() {

    context.checking(new Expectations() {{
      oneOf(fooRegisterView).getUser();
      will(returnValue(new User("Test", "password")));

      oneOf(fooRpcService).registerUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(fooRegisterView).clearFields();
      oneOf(fooRegisterView).setNotification(with("Registration was successful!"));
    }});

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onSuccess(null);
  }

  @Test
  public void usernameIsEmpty() {

    pretendEnteredUserIs(new User("", "password"));
    registerPresenter.registerUser();
  }

  @Test
  public void usernameIsTooLong() {

    pretendEnteredUserIs(new User("TestTestTestTestTestTest", "password"));
    registerPresenter.registerUser();
  }

  @Test
  public void passwordIsEmtpy() {

    pretendEnteredUserIs(new User("Test", ""));
    registerPresenter.registerUser();
  }

  @Test
  public void passwordIsTooShort() {

    pretendEnteredUserIs(new User("Test", "pass"));
    registerPresenter.registerUser();
  }

  @Test
  public void passwordIsTooLong() {

    pretendEnteredUserIs(new User("Test", "passwordpasswordpassword"));
    registerPresenter.registerUser();
  }

  @Test
  public void wrongUsernameExceptionIsCaught() {

    pretendEnteredUserDataIsSent(new User("Test", "password"));

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onFailure(new WrongUsernameException());
  }

  @Test
  public void wrongPasswordExceptionIsCaught() {

    pretendEnteredUserDataIsSent(new User("Test", "password"));

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onFailure(new WrongPasswordException());
  }

  private void pretendEnteredUserIs(final User user) {

    context.checking(new Expectations() {{
      oneOf(fooRegisterView).getUser();
      will(returnValue(user));

      oneOf(fooRegisterView).clearFields();
      oneOf(fooRegisterView).setNotification(with(notificationMessage));
    }});
  }

  private void pretendEnteredUserDataIsSent(final User user) {

    context.checking(new Expectations(){{
      oneOf(fooRegisterView).getUser();
      will(returnValue(user));

      oneOf(fooRpcService).registerUser(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(fooRegisterView).clearFields();
      oneOf(fooRegisterView).setNotification(with(notificationMessage));
    }});
  }
}
