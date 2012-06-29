package com.clouway.gwt.requestfactory.contacts.client.addcontact.ui;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface AddContactView {

  public interface Presenter {
    PersonRequest getPersonRequest();
    PersonProxy getPersonProxy();
    void save();
  }

  void setPresenter(Presenter presenter);
  void goToViewContactsPlace();
}
