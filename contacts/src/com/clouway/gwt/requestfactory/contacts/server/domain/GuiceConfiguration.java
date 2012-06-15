package com.clouway.gwt.requestfactory.contacts.server.domain;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class GuiceConfiguration extends GuiceServletContextListener {

  protected Injector getInjector() {

    return Guice.createInjector(
            new ServletModule() {
              protected void configureServlets() {
                bind(DatabaseHelper.class).in(Singleton.class);
                bind(PersonRepository.class).to(PersonRepositoryImpl.class);
              }
            }
    );
  }
}
