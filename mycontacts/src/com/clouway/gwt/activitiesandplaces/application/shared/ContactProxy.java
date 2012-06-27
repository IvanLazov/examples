package com.clouway.gwt.activitiesandplaces.application.shared;

import com.clouway.gwt.activitiesandplaces.application.server.domain.Contact;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@ProxyFor(Contact.class)
public interface ContactProxy extends EntityProxy {

  Long getId();
  String getName();
  String getAddress();

  void setName(String name);
  void setAddress(String address);
}
