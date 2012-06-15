package com.clouway.gwt.requestfactory.contacts.server.domain;

import javax.persistence.Entity;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Entity
public class Person {

  private Long id;

  private String firstname;
  private String lastname;
  private Integer age;
  private Long version;

  public Person() {

  }

  public Person(Long id, String firstname, String lastname, Integer age, Long version) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.age = age;
    this.version = version;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getAge() {
    return age;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Long getVersion() {
    return version;
  }
}
