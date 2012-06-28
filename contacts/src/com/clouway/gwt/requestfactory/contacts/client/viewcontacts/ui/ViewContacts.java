package com.clouway.gwt.requestfactory.contacts.client.viewcontacts.ui;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ViewContacts {

  public interface Presenter {
    void deleteContact(int rowIndex, Long id);
  }

  void setPresenter(Presenter presenter);
  void loadContacts(List<PersonProxy> contacts);
  void deleteContact(int rowIndex);
  void loadingNotification(boolean visible);
}
