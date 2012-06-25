package com.clouway.gwt.requestfactory.contacts.server.domain;

import com.google.inject.Inject;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class PersonServiceImpl implements PersonService {

  private PersonRepository repository;

  @Inject
  public PersonServiceImpl(PersonRepository repository) {
    this.repository = repository;
  }

  public void save(Person person) {
    repository.save(person);
  }

  public Person findPerson(Long id) {
    return repository.findPerson(id);
  }

  public List<Person> findAll() {
    return repository.findAll();
  }

  public void delete(Long id) {
    repository.delete(id);
  }

  public void update(Person person) {
    repository.update(person);
  }
}
