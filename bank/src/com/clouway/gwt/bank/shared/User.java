package com.clouway.gwt.bank.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class User implements IsSerializable {

  private String username;
  private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
