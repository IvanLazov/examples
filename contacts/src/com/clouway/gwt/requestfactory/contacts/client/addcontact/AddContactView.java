package com.clouway.gwt.requestfactory.contacts.client.addcontact;

import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface AddContactView {

  public interface Presenter {
    void save();
  }

  void setPresenter(Presenter presenter);
  void showWindow();
  void clearInputFields();
  RequestFactoryEditorDriver getDriver();
}
