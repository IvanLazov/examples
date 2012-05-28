package com.clouway.gwt.bank.shared;


import java.io.Serializable;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class User implements Serializable {

  private String username;
  private String password;

  public User() {
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
