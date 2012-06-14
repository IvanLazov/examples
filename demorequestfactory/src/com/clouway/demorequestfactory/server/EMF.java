package com.clouway.demorequestfactory.server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public final class EMF {

  private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");

  private EMF() {

  }

  public static EntityManagerFactory get() {
    return emfInstance;
  }
}
