package com.clouway.requestfactory.sample.server;

import com.clouway.requestfactory.sample.server.domain.Pizza;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class PizzaLocator extends Locator<Pizza, Long> {
  @Override
  public Pizza create(Class<? extends Pizza> clazz) {
    return new Pizza();
  }

  @Override
  public Pizza find(Class<? extends Pizza> clazz, Long id) {
    return getPizzaDao().findById(id);
  }

  private PizzaDao getPizzaDao() {
    return new PizzaDao();
  }

  @Override
  public Class<Pizza> getDomainType() {
    return Pizza.class;
  }

  @Override
  public Long getId(Pizza domainObject) {
    return domainObject.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }

  @Override
  public Object getVersion(Pizza domainObject) {
    return domainObject.getVersion();
  }
}
