package com.clouway.gwt.requestfactory.contacts.client.addcontact.ui;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface AddContactView {

  public interface Presenter {
    void save();
  }

  void setPresenter(Presenter presenter);
  void fire();
}
