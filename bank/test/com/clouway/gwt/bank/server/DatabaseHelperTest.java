package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.server.user.UserResultSetBuilder;
import com.clouway.gwt.bank.shared.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class DatabaseHelperTest {

  private SampleConnectionProvider connectionProvider = new SampleConnectionProvider("bankTest");
  private DatabaseHelper databaseHelper;

  private String username = "Test";
  private String password = "password";

  @Before
  public void setUp() {
    databaseHelper = new DatabaseHelper(connectionProvider);

    databaseHelper.executeQuery("DELETE FROM session");
    databaseHelper.executeQuery("DELETE FROM account");
    databaseHelper.executeQuery("DELETE FROM user");

    databaseHelper.executeQuery("INSERT INTO user(username,password) VALUES (?,?)", username, password);
  }

  @Test
  public void testInsertStatement() {

    User actual = databaseHelper.executeQuery("SELECT * FROM user WHERE username=? AND password=?", new UserResultSetBuilder(), username, password);
    assertThat(username, is(equalTo(actual.getUsername())));
    assertThat(password, is(equalTo(actual.getPassword())));
  }

  @Test
  public void testDeleteStatement() {

    databaseHelper.executeQuery("DELETE FROM user WHERE username=?", username);
    User actual = databaseHelper.executeQuery("SELECT * FROM user WHERE username=? AND password=?", new UserResultSetBuilder(), username, password);
    assertNull(actual);
  }

  @Test
  public void testUpdateStatement() {

    databaseHelper.executeQuery("UPDATE user SET password=? WHERE username=?", "pass", username);
    User user = databaseHelper.executeQuery("SELECT * FROM user WHERE username=?", new UserResultSetBuilder(), username);
    assertThat(user.getUsername(), is(equalTo(username)));
    assertThat(user.getPassword(), is(equalTo("pass")));
  }

  @After
  public void tearDown() {
    connectionProvider.close();
  }
}
