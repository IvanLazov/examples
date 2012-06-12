package com.clouway.gwt.bank.server;

import com.google.inject.Provider;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class SampleConnectionProvider implements Provider<Connection> {

  private final MysqlDataSource dataSource = new MysqlDataSource();

  public SampleConnectionProvider() {
    dataSource.setServerName("localhost");
    dataSource.setDatabaseName("bankTest");
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
}
