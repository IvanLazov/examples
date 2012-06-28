package com.clouway.gwt.requestfactory.contacts.client.editcontact.ui;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface EditContactView {

  public interface Presenter {
    void save();
  }

  void edit(PersonProxy personProxy, PersonRequest request);
  void goToViewContactsPlace();
  void setPresenter(Presenter presenter);
}
