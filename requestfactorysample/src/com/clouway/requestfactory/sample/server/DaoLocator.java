package com.clouway.requestfactory.sample.server;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class DaoLocator implements ServiceLocator {
  @Override
  public Object getInstance(Class<?> clazz) {
    // Create DAO. Instance is cached by GWT so make the implementation is stateless.
    return new PizzaDao();
  }
}
