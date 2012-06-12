package com.clouway.gwt.bank.server.session;

import com.clouway.gwt.bank.server.DatabaseHelper;
import com.clouway.gwt.bank.server.SampleModule;
import com.clouway.gwt.bank.shared.AuthorizedUser;
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
public class SessionRepositoryImplTest {

  private Injector injector = Guice.createInjector(new SampleModule());
  private final User user = new User(1, "Test", "password");

  private SessionRepositoryImpl repository;

  @Before
  public void setUp() {
    DatabaseHelper databaseHelper = injector.getInstance(DatabaseHelper.class);
    databaseHelper.executeQuery("DELETE FROM session");
    databaseHelper.executeQuery("DELETE FROM account");
    databaseHelper.executeQuery("DELETE FROM user");

    databaseHelper.executeQuery("INSERT INTO user(userId,username,password) VALUES(?,?,?)", user.getUserId(), user.getUsername(), user.getPassword());

    repository = new SessionRepositoryImpl(databaseHelper);
  }

  @Test
  public void happyPath() {

    String tokenValue = "abcd1234";

    repository.createSession(tokenValue, user.getUserId(), user.getUsername());

    AuthorizedUser authorizedUser = repository.getUser(tokenValue);

    assertThat(authorizedUser.getTokenValue(), is(equalTo(tokenValue)));
    assertThat(authorizedUser.getUserId(), is(equalTo(user.getUserId())));
    assertThat(authorizedUser.getUsername(), is(equalTo(user.getUsername())));
  }
}
