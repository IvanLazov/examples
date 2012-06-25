package com.clouway.gwt.requestfactory.contacts.server.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class PersonResultSetBuilderImpl implements ResultSetBuilder<Person> {

  public Person build(ResultSet resultSet) {
    Person person = null;

    try {
      person = new Person(resultSet.getLong("id"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getInt("age"), resultSet.getLong("id"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return person;
  }
}
