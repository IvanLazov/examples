package com.clouway.gwt.requestfactory.contacts.client.addcontact.presenter;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactView;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactPresenter implements AddContactView.Presenter {

  private final AddContactView view;

  public AddContactPresenter(AddContactView view) {
    this.view = view;
    this.view.setPresenter(this);
  }

  public void save() {
    view.fire();
  }
}
