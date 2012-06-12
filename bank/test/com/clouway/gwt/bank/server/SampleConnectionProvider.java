package com.clouway.gwt.bank.server;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class SampleConnectionProvider implements Provider<Connection> {

  private Connection connection;
  private MysqlDataSource dataSource = new MysqlDataSource();

  public SampleConnectionProvider(String databaseName) {
    dataSource.setServerName("localhost");
    dataSource.setDatabaseName(databaseName);
    dataSource.setUser("clouway");
    dataSource.setPassword("clouway.com");
  }

  public Connection get() {
    Connection connection = null;

    try {
      connection = dataSource.getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public void close() {

    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
