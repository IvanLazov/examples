package com.clouway.gwt.requestfactory.contacts.shared;

import com.clouway.gwt.requestfactory.contacts.server.domain.Person;
import com.clouway.gwt.requestfactory.contacts.server.domain.PersonLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@ProxyFor(value = Person.class, locator = PersonLocator.class)
public interface PersonProxy extends EntityProxy {

  void setFirstname(String firstname);
  String getFirstname();

  void setLastname(String lastname);
  String getLastname();

  void setAge(Integer age);
  Integer getAge();

  Long getId();
}
