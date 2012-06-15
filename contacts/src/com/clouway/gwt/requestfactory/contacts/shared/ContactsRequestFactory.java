package com.clouway.gwt.requestfactory.contacts.shared;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ContactsRequestFactory extends RequestFactory {

  PersonRequest personRequest();
}
