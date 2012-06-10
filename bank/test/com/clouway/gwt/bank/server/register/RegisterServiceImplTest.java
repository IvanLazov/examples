package com.clouway.gwt.bank.server.register;

import com.clouway.gwt.bank.server.account.AccountRepository;
import com.clouway.gwt.bank.shared.exceptions.UsernameAlreadyTakenException;
import com.clouway.gwt.bank.shared.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameException;
import com.clouway.gwt.bank.shared.User;
import com.clouway.gwt.bank.server.user.UserRepository;
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
public class RegisterServiceImplTest {

  private Mockery context = new JUnit4Mockery();
  private UserRepository userRepository = context.mock(UserRepository.class);
  private AccountRepository accountRepository = context.mock(AccountRepository.class);

  private RegisterServiceImpl registerService;

  @Before
  public void setUp() {
    registerService = new RegisterServiceImpl(userRepository, accountRepository);
  }

  @Test
  public void happyPath() {

    final long userId = 1;
    final User user = new User("Test", "password");

    context.checking(new Expectations(){{
      oneOf(userRepository).getUser(user.getUsername());
      will(returnValue(null));

      oneOf(userRepository).register(user);
      will(returnValue(userId));

      oneOf(accountRepository).createAccount(userId);
    }});

    registerService.registerUser(user);
  }

  @Test(expected = WrongUsernameException.class)
  public void userCantRegisterWithEmptyUsername() {
    registerService.registerUser(new User("", "password"));
  }

  @Test(expected = WrongUsernameException.class)
  public void userCantRegisterWithUsernameLongerThanTwentyCharacters() {
    registerService.registerUser(new User("TestTestTestTestTestTest", "password"));
  }

  @Test(expected = WrongUsernameException.class)
  public void userCantRegisterWithUsernameContainingSpecialCharacters() {
    registerService.registerUser(new User("Test!@#", "password"));
  }

  @Test(expected = WrongPasswordException.class)
  public void userCantRegisterWithEmptyPassword() {
    registerService.registerUser(new User("Test", ""));
  }

  @Test(expected = WrongPasswordException.class)
  public void userCantRegisterWithPasswordLessThanSixCharacters() {
    registerService.registerUser(new User("Test", "pass"));
  }

  @Test(expected = WrongPasswordException.class)
  public void userCantRegisterWithPasswordLongerThanTwentyCharacters() {
    registerService.registerUser(new User("Test", "passwordpasswordpassword"));
  }

  @Test(expected = WrongPasswordException.class)
  public void userCantRegisterWithPasswordContainingSpecialCharacters() {
    registerService.registerUser(new User("Test", "pass!@#$"));
  }

  @Test(expected = UsernameAlreadyTakenException.class)
  public void userCantRegisterWithAlreadyRegisteredUsername() {

    final User user = new User("Test", "password");

    context.checking(new Expectations(){{
      oneOf(userRepository).getUser(user.getUsername());
      will(returnValue(user));
    }});

    registerService.registerUser(user);
  }
}
