package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ViewContacts {

  public interface Presenter {

  }

  void setPresenter(Presenter presenter);
  void loadContacts(List<PersonProxy> contacts);
  void clearContacts();
}
