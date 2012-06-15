package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Contacts implements EntryPoint {

  public void onModuleLoad() {

    final ContactsRequestFactory requestFactory = GWT.create(ContactsRequestFactory.class);

    AddContactViewImpl addContactView = new AddContactViewImpl();
    AddContactPresenter addContactPresenter = new AddContactPresenter(requestFactory, addContactView);

    addContactPresenter.go(RootPanel.get());
  }
}
