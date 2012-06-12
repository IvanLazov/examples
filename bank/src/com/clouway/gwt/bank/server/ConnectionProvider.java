package com.clouway.gwt.bank.server;

import com.google.inject.Provider;

import java.sql.Connection;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ConnectionProvider implements Provider<Connection> {

  public Connection get() {
    return ConnectionFilter.connectionThreadLocal.get();
  }
}
