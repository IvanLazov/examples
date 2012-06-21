package com.clouway.gwt.editorframework.sample.client.nestedbinding;

import com.clouway.gwt.editorframework.sample.client.singlebinding.Email;

import java.io.Serializable;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class Customer implements Serializable {

  private String id;
  private String firstName;
  private String lastName;
  private Email email;

  public Customer() {
  }

  public Customer(String id, String firstName, String lastName, Email email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(Email email) {
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Email getEmail() {
    return email;
  }
}
