package com.clouway.gwt.requestfactory.contacts.client;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface AddContactView {

  public interface Presenter {
    void save();
  }

  void setPresenter(Presenter presenter);

  String getFirstname();
  String getLastname();
  String getAge();

  void clearInputFields();
}
