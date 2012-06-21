package com.clouway.gwt.editorframework.sample.client.singlebinding;

import java.io.Serializable;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class Email implements Serializable {

  private String email;

  public Email() {
  }

  public Email(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
