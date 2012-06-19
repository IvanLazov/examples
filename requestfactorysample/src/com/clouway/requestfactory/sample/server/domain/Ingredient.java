package com.clouway.requestfactory.sample.server.domain;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class Ingredient {

  private Long id;
  private Long version;

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

  public Long getId() {
    return id;
  }

  public Long getVersion() {
    return version;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setVersion(Long version) {
    this.version = version;
  }
}
