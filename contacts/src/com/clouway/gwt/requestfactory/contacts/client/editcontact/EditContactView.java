package com.clouway.gwt.requestfactory.contacts.client.editcontact;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface EditContactView {

  void edit(PersonProxy personProxy, PersonRequest personRequest);

  void fireRequest();

  public interface Presenter {
    void save();
  }

  void setPresenter(Presenter presenter);
  void showNotificationWindow();
  RequestFactoryEditorDriver getDriver();
}
