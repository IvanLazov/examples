package com.clouway.gwt.gwtbank.shared;

import java.io.Serializable;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class User implements Serializable {

  private String username;
  private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
