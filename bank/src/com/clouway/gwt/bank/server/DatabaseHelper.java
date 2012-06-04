package com.clouway.gwt.bank.server;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class DatabaseHelper {

  private MysqlDataSource dataSource = new MysqlDataSource();

  public DatabaseHelper() {
    dataSource.setServerName("localhost");
    dataSource.setDatabaseName("bank");
    dataSource.setUser("clouway");
    dataSource.setPassword("clouway.com");
  }

  public long executeQuery(String query, Object... params) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    long autoIncrementKey = -1;

    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
      fillPreparedStatement(preparedStatement, params);
      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        autoIncrementKey = resultSet.getLong(1);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePreparedStatement(preparedStatement);
      closeConnection(connection);
    }

    return autoIncrementKey;
  }

  public <T> T executeQuery(String query, ResultSetMapper<T> resultSetMapper, Object... params) {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    T object = null;

    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(query);
      fillPreparedStatement(preparedStatement, params);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        object = resultSetMapper.map(resultSet);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePreparedStatement(preparedStatement);
      closeConnection(connection);
    }

    return object;
  }

  private void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private void closePreparedStatement(PreparedStatement preparedStatement) {
    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
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
