package com.clouway.gwt.gwtbank.client;


import com.clouway.gwt.gwtbank.shared.User;
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
public class RegisterNewUserTest {

  interface RegisterView {

    public interface Presenter {
      void register(User user);
    }

    User getUser();
  }

  class RegisterPresenter implements RegisterView.Presenter {

    private final RegisterView registerView;
    private final GWTBankServiceAsync bankService;
    private final AsyncCallback asyncCallback;

    public RegisterPresenter(RegisterView registerView, GWTBankServiceAsync bankService, AsyncCallback asyncCallback) {
      this.registerView = registerView;
      this.bankService = bankService;
      this.asyncCallback = asyncCallback;
    }

    public void register(User user) {
      User user1 = registerView.getUser();
      bankService.register(user1, asyncCallback);
    }
  }

  RegisterPresenter registerPresenter;
  Mockery mockery = new JUnit4Mockery();
  RegisterView mockRegisterView = mockery.mock(RegisterView.class);
  GWTBankServiceAsync mockGwtBankService = mockery.mock(GWTBankServiceAsync.class);
  AsyncCallback asyncCallback = mockery.mock(AsyncCallback.class);

  @Before
  public void setUp() {
    registerPresenter = new RegisterPresenter(mockRegisterView, mockGwtBankService, asyncCallback);
  }

  @Test
  public void callAsyncCallback() {

    final User user = new User("555", "123456");

    mockery.checking(new Expectations(){{
      oneOf(mockRegisterView).getUser();
      will(returnValue(user));
      oneOf(mockGwtBankService).register(user,asyncCallback);
    }});

    registerPresenter.register(user);
  }
}
