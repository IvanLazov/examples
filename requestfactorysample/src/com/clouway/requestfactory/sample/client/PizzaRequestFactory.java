package com.clouway.requestfactory.sample.client;

import com.clouway.requestfactory.sample.server.DaoLocator;
import com.clouway.requestfactory.sample.server.PizzaDao;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface PizzaRequestFactory extends RequestFactory {

  @Service(value = PizzaDao.class, locator = DaoLocator.class)
  public interface PizzaRequestContext extends RequestContext {

    Request<PizzaProxy> findById(Long id);

    Request<Void> save(PizzaProxy pizza);
  }

  PizzaRequestContext context();
}
