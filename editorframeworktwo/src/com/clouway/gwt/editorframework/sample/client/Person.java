package com.clouway.gwt.editorframework.sample.client;

import javax.validation.constraints.Size;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class Person {

  private String firstName;
  private String lastName;
  private final Address address = new Address();

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Address getAddress() {
    return address;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
