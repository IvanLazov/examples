package com.clouway.gwt.requestfactory.contacts.server.domain;

//import com.google.inject.Inject;
//import com.google.inject.Provider;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class DatabaseHelper {

  //@Inject
  //private Provider<Connection> connection;

  private final MysqlDataSource dataSource = new MysqlDataSource();

  public DatabaseHelper() {
    dataSource.setServerName("localhost");
    dataSource.setDatabaseName("contacts");
    dataSource.setUser("clouway");
    dataSource.setPassword("clouway.com");
  }

  public long executeQuery(String query, Object... params) {

    long autoIncrementKey = -1;

    Connection connection = null;

    try {
      connection = dataSource.getConnection();
      //PreparedStatement preparedStatement = connection.get().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
      PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
      fillPreparedStatement(preparedStatement, params);
      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        autoIncrementKey = resultSet.getLong(1);
      }

      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    return autoIncrementKey;
  }

  public <T> T executeQuery(String query, ResultSetBuilder<T> resultSetBuilder, Object... params) {

    T object = null;

    Connection connection = null;

    try {
      connection = dataSource.getConnection();
      //PreparedStatement preparedStatement = connection.get().prepareStatement(query);
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      fillPreparedStatement(preparedStatement, params);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        object = resultSetBuilder.build(resultSet);
      }

      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return object;
  }

  private void fillPreparedStatement(PreparedStatement preparedStatement, Object[] params) {

    for (int i = 0; i < params.length; i++) {
      try {
        preparedStatement.setObject(i + 1, params[i]);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
