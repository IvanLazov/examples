package com.clouway.requestfactory.sample.client;

import com.clouway.requestfactory.sample.server.domain.Ingredient;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@ProxyFor(value = Ingredient.class)
public interface IngredientProxy extends EntityProxy {

  public String getName();
  public void setName(String name);

  public boolean isVegan();
  public void setVegan(boolean vegan);
}
