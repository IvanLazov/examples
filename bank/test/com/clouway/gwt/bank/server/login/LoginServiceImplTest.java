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

  /*@Test
  public void happyPath() {

    final String token = "abcd1234";
    final User user = new User("Test", "password");

    context.checking(new Expectations(){{
      oneOf(userRepository).getUser(user.getUsername());
      will(returnValue(user));

      oneOf(sessionRepository).createSession(token, user.getUserId(), user.getUsername());
      oneOf(sessionRepository).getUser(token);
    }});

    loginService.loginUser(user);
  }*/

  @Test(expected = WrongUsernameOrPasswordException.class)
  public void userCantLoginWithUnexistingUser() {

    final User user = new User("Test", "password");

    context.checking(new Expectations(){{
      oneOf(userRepository).getUser(user.getUsername());
      will(returnValue(null));
    }});

    loginService.loginUser(user);
  }
}
