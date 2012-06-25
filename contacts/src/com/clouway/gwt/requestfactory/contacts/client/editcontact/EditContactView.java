package com.clouway.gwt.requestfactory.contacts.client.editcontact;

import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface EditContactView {

  public interface Presenter {
    void save();
  }

  void setPresenter(Presenter presenter);
  void showNotificationWindow();
  RequestFactoryEditorDriver getDriver();
}
