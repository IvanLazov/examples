package com.clouway.gwt.bank.client.register;

import com.clouway.gwt.bank.client.BankServiceAsync;
import com.clouway.gwt.bank.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
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
public class RegisterPresenterTest {

  private Mockery mockery = new JUnit4Mockery();
  private BankServiceAsync mockRpcService = mockery.mock(BankServiceAsync.class);
  private RegisterView mockRegisterView = mockery.mock(RegisterView.class);

  @Test
  public void callRpcService() {

    RegisterPresenter registerPresenter = new RegisterPresenter(mockRpcService, mockRegisterView);

    final InstanceCatcher<User> userInstanceCatcher = new InstanceCatcher<User>();
    final InstanceCatcher<AsyncCallback<User>> asyncCallbackInstanceCatcher = new InstanceCatcher<AsyncCallback<User>>();
    final User user = new User("Ivan", "123456");

    mockery.checking(new Expectations(){{
      oneOf(mockRpcService).registerUser(with(userInstanceCatcher),with(asyncCallbackInstanceCatcher));
    }});

    registerPresenter.onRegisterUser(user);
  }

  class InstanceCatcher<T> extends BaseMatcher<T> {

    private T instance;

    @Override
    public boolean matches(Object item) {
      instance = (T) item;
      return true;
    }

    @Override
    public void describeTo(Description description) {
    }
  }
}
