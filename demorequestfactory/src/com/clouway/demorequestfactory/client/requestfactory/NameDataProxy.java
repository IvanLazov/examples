package com.clouway.demorequestfactory.client.requestfactory;

import com.clouway.demorequestfactory.server.domain.NameData;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */

@ProxyFor(NameData.class)
public interface NameDataProxy extends EntityProxy {

  Long getId();

  Integer getVersion();

  void setName(String name);

  String getName();
}
