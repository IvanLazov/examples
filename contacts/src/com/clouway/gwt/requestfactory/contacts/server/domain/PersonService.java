package com.clouway.gwt.requestfactory.contacts.server.domain;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface PersonService {

  void save(Person person);

  Person findPerson(Long id);

  List<Person> findAll();

  void delete(Long id);
}
