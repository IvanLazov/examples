package com.clouway.gwt.bank.server;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import java.sql.Connection;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class SampleModule extends AbstractModule {

  protected void configure() {
    bind(Connection.class).toProvider(SampleConnectionProvider.class).in(Singleton.class);
  }
}
