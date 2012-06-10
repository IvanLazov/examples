package com.clouway.gwt.bank.server.user;

import com.clouway.gwt.bank.server.DatabaseHelper;
import com.clouway.gwt.bank.shared.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class UserRepositoryTest {

  private final User user = new User("Test", "password");
  private DatabaseHelper databaseHelper = new DatabaseHelper();
  private UserRepositoryImpl userRepository;

  @Before
  public void setUp() {
    databaseHelper.executeQuery("DELETE FROM account");
    databaseHelper.executeQuery("DELETE FROM user");
    userRepository = new UserRepositoryImpl(databaseHelper);
  }

  @Test
  public void registerUser() {

    userRepository.register(user);
    User actual = userRepository.getUser(user.getUsername());

    assertThat(user.getUsername(), is(equalTo(actual.getUsername())));
    assertThat(user.getPassword(), is(equalTo(actual.getPassword())));
  }
}
