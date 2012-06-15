package com.clouway.gwt.requestfactory.contacts.server.domain;

import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class PersonLocator extends Locator<Person, Long> {

  private final PersonRepository repository;

  @Inject
  public PersonLocator(PersonRepository repository) {
    this.repository = repository;
  }

  public Person create(Class<? extends Person> clazz) {
    try {
      return clazz.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Person find(Class<? extends Person> clazz, Long id) {
    // TODO: What should happen if there is no person with this id ?
    return repository.findPerson(id);
  }

  public Class<Person> getDomainType() {
    return Person.class;
  }

  public Long getId(Person domainObject) {
    return domainObject.getId();
  }

  public Class<Long> getIdType() {
    return Long.class;
  }

  public Object getVersion(Person domainObject) {
    return domainObject.getVersion();
  }
}
