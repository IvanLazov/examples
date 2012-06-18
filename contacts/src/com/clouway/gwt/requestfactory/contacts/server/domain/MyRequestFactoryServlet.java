package com.clouway.gwt.requestfactory.contacts.server.domain;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.DefaultExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Singleton
public class MyRequestFactoryServlet extends RequestFactoryServlet {

  @Inject
  public MyRequestFactoryServlet(MyServiceLayerDecorator decorator) {
    super(new DefaultExceptionHandler(), decorator);
  }
}
