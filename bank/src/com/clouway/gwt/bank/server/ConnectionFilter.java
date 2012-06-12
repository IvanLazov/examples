package com.clouway.gwt.bank.server;

import com.google.inject.Singleton;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Singleton
public class ConnectionFilter implements Filter {

  private MysqlConnectionPoolDataSource dataSource;
  public static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

  public void init(FilterConfig filterConfig) throws ServletException {
    dataSource = new MysqlConnectionPoolDataSource();
    dataSource.setServerName("localhost");
    dataSource.setDatabaseName("bank");
    dataSource.setUser("clouway");
    dataSource.setPassword("clouway.com");
  }

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    try {
      Connection connection = dataSource.getConnection();
      connectionThreadLocal.set(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    filterChain.doFilter(servletRequest, servletResponse);

    Connection openedConnection = connectionThreadLocal.get();

    if (openedConnection != null) {
      try {
        openedConnection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void destroy() {
  }
}
