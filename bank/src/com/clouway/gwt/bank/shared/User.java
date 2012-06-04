package com.clouway.gwt.bank.shared;


import java.io.Serializable;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class User implements Serializable {

  private long userId;
  private String username;
  private String password;

  public User() {
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(long userId, String username, String password) {
    this.userId = userId;
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public long getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (userId != user.userId) return false;
    if (password != null ? !password.equals(user.password) : user.password != null) return false;
    if (username != null ? !username.equals(user.username) : user.username != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = (int) (userId ^ (userId >>> 32));
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }
}
