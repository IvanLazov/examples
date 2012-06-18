package com.clouway.requestfactory.sample.server.domain;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class Ingredient {

  private String name;
  private boolean vegan;

  public String getName() {
    return name;
  }

  public boolean isVegan() {
    return vegan;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setVegan(boolean vegan) {
    this.vegan = vegan;
  }
}
