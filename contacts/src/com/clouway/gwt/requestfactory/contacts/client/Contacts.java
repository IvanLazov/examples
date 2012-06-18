package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class Contacts implements EntryPoint {

  public void onModuleLoad() {

    EventBus eventBus = new SimpleEventBus();

    final ContactsRequestFactory requestFactory = GWT.create(ContactsRequestFactory.class);
    requestFactory.initialize(eventBus);

    AddContactViewImpl addContactView = new AddContactViewImpl();
    AddContactPresenter addContactPresenter = new AddContactPresenter(requestFactory, addContactView);

    addContactPresenter.go(RootPanel.get());
  }
}
