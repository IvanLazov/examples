package com.clouway.gwt.bank.server.user;

import com.clouway.gwt.bank.server.DatabaseHelper;
import com.clouway.gwt.bank.server.SampleModule;
import com.clouway.gwt.bank.shared.User;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class UserRepositoryTest {

  private Injector injector = Guice.createInjector(new SampleModule());
  private UserRepositoryImpl userRepository;

  private final User user = new User(1, "Test", "password");

  @Before
  public void setUp() {
    DatabaseHelper databaseHelper = injector.getInstance(DatabaseHelper.class);
    databaseHelper.executeQuery("DELETE FROM session");
    databaseHelper.executeQuery("DELETE FROM account");
    databaseHelper.executeQuery("DELETE FROM user");

    userRepository = new UserRepositoryImpl(databaseHelper);
  }

  @Test
  public void registerUser() {

    userRepository.register(user);
    User actual = userRepository.getUser(user.getUsername());

    assertThat(user.getUserId(), is(equalTo(user.getUserId())));
    assertThat(user.getUsername(), is(equalTo(actual.getUsername())));
    assertThat(user.getPassword(), is(equalTo(actual.getPassword())));
  }
}
