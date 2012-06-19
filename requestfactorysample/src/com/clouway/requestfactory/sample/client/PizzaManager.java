package com.clouway.requestfactory.sample.client;

import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class PizzaManager {

  private final PizzaRequestFactory factory;

  public PizzaManager(PizzaRequestFactory factory) {
    this.factory = factory;
  }

  public void findById(Long id, Receiver<PizzaProxy> receiver) {
    factory.context().findById(id).with("ingredients").fire(receiver);
  }
}
