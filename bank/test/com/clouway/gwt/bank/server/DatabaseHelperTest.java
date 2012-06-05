package com.clouway.gwt.bank.server;

import com.clouway.gwt.bank.shared.User;
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

  private String username = "Test";
  private String password = "password";
  private DatabaseHelper databaseHelper;

  @Before
  public void setUp() {
    databaseHelper = new DatabaseHelper();
    databaseHelper.executeQuery("DELETE FROM user");
    databaseHelper.executeQuery("DELETE FROM account");
    databaseHelper.executeQuery("INSERT INTO user(username,password) VALUES (?,?)", username, password);
  }

  @Test
  public void testInsertStatement() {

    User actual = databaseHelper.executeQuery("SELECT * FROM user WHERE username=? AND password=?", new UserResultSetMapper(), username, password);
    assertThat(username, is(equalTo(actual.getUsername())));
    assertThat(password, is(equalTo(actual.getPassword())));
  }

  @Test
  public void testDeleteStatement() {

    databaseHelper.executeQuery("DELETE FROM user WHERE username=?", username);
    User actual = databaseHelper.executeQuery("SELECT * FROM user WHERE username=? AND password=?", new UserResultSetMapper(), username, password);
    assertNull(actual);
  }

  @Test
  public void testUpdateStatement() {

    databaseHelper.executeQuery("UPDATE user SET password=? WHERE username=?", "pass", username);
    User user = databaseHelper.executeQuery("SELECT * FROM user WHERE username=?", new UserResultSetMapper(), username);
    assertThat(user.getPassword(), is(equalTo("pass")));
  }
}