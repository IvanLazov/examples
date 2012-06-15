package com.clouway.gwt.requestfactory.contacts.server.domain;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface PersonRepository {

  Person findPerson(Long id);
  void save(Person person);
}
