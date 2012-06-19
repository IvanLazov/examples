package com.clouway.gwt.requestfactory.contacts.server.domain;

import com.google.inject.Inject;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class PersonRepositoryImpl implements PersonRepository {

  private final DatabaseHelper databaseHelper;
  private PersonResultSetBuilderImpl resultSetBuilder = new PersonResultSetBuilderImpl();

  @Inject
  public PersonRepositoryImpl(DatabaseHelper databaseHelper) {
    this.databaseHelper = databaseHelper;
  }

  public Person findPerson(Long id) {
    return databaseHelper.executeQuery("SELECT * FROM person WHERE id=?", resultSetBuilder, id);
  }

  public void save(Person person) {
    databaseHelper.executeQuery("INSERT INTO person(firstname,lastname,age) VALUES(?,?,?)", person.getFirstname(), person.getLastname(), person.getAge());
  }

  @Override
  public List<Person> findAll() {
    return databaseHelper.executeQueryForList("SELECT * FROM person", resultSetBuilder);
  }
}
