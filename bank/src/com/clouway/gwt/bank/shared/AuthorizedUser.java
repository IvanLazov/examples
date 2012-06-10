package com.clouway.gwt.bank.shared;

import java.io.Serializable;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AuthorizedUser implements Serializable {

  private String tokenValue;
  private long userId;
  private String username;

  public AuthorizedUser() {
  }

  public AuthorizedUser(String tokenValue, long userId, String username) {
    this.tokenValue = tokenValue;
    this.userId = userId;
    this.username = username;
  }

  public String getTokenValue() {
    return tokenValue;
  }

  public long getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AuthorizedUser authorizedUser = (AuthorizedUser) o;

    if (userId != authorizedUser.userId) return false;
    if (tokenValue != null ? !tokenValue.equals(authorizedUser.tokenValue) : authorizedUser.tokenValue != null) return false;
    if (username != null ? !username.equals(authorizedUser.username) : authorizedUser.username != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = tokenValue != null ? tokenValue.hashCode() : 0;
    result = 31 * result + (int) (userId ^ (userId >>> 32));
    result = 31 * result + (username != null ? username.hashCode() : 0);
    return result;
  }
}
