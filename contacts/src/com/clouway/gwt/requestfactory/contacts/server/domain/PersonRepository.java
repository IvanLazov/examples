package com.clouway.gwt.requestfactory.contacts.server.domain;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface PersonRepository {

  Person findPerson(Long id);
  void save(Person person);
  void delete(Long id);
  List<Person> findAll();
  void update(Person person);
}
