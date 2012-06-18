package com.clouway.gwt.requestfactory.contacts.server.domain;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface PersonService {

  void save(Person person);

  Person findPerson(Long id);
}
