package com.clouway.gwt.requestfactory.contacts.server.domain;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class MyServiceLocator implements ServiceLocator {

  private Injector injector;

  @Inject
  public MyServiceLocator(Injector injector) {
    this.injector = injector;
  }

  public Object getInstance(Class<?> clazz) {
    return injector.getInstance(clazz);
  }
}
