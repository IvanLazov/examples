package com.clouway.requestfactory.sample.client;

import com.clouway.requestfactory.sample.server.domain.Pizza;
import com.clouway.requestfactory.sample.server.PizzaLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@ProxyFor(value = Pizza.class, locator = PizzaLocator.class)
public interface PizzaProxy extends EntityProxy {

  Long getId();

  public String getName();
  public void setName(String name);

  public List<IngredientProxy> getIngredients();
  public void setIngredients(List<IngredientProxy> ingredients);
}
