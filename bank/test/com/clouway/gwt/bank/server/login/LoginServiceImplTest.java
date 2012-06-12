package com.clouway.gwt.bank.server.login;

import com.clouway.gwt.bank.server.session.SessionRepository;
import com.clouway.gwt.bank.server.user.UserRepository;
import com.clouway.gwt.bank.shared.User;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameOrPasswordException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class LoginServiceImplTest {

  private Mockery context = new JUnit4Mockery();

  private UserRepository userRepository = context.mock(UserRepository.class);
  private SessionRepository sessionRepository = context.mock(SessionRepository.class);

  private LoginServiceImpl loginService;

  @Before
  public void setUp() {
    loginService = new LoginServiceImpl(userRepository, sessionRepository);
  }

  @Test
  public void happyPath() {

    /*final User user = new User(1, "Test", "password");

    context.checking(new Expectations(){{
      oneOf(userRepository).getUser(user.getUsername());
      will(returnValue(user));

      oneOf(sessionRepository).createSession(rpcToken.getToken(), user.getUserId(), user.getUsername());
      oneOf(sessionRepository).getUser(rpcToken.getToken());
    }});

    AuthorizedUser authorizedUser = loginService.loginUser(user);

    assertThat(rpcToken.getToken(), is(equalTo(authorizedUser.getTokenValue())));
    assertThat(authorizedUser.getUserId(), is(equalTo(user.getUserId())));
    assertThat(authorizedUser.getUsername(), is(equalTo(user.getUsername())));*/

    fail();
  }

  @Test(expected = WrongUsernameOrPasswordException.class)
  public void userCantLoginWithUnExistingUser() {

    final User user = new User("Test", "password");

    context.checking(new Expectations(){{
      oneOf(userRepository).getUser(user.getUsername());
      will(returnValue(null));
    }});

    loginService.loginUser(user);
  }
}
