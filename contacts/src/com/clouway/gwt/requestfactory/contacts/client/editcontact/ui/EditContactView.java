package com.clouway.gwt.requestfactory.contacts.client.editcontact.ui;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface EditContactView {

  public interface Presenter {
    void onEditedPerson(RequestContext context);
  }

  void edit(PersonProxy personProxy, PersonRequest request);
  void goToViewContactsPlace();
  void setPresenter(Presenter presenter);
}
