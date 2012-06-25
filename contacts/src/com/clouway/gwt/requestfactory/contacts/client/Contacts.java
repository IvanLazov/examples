package com.clouway.gwt.requestfactory.contacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Contacts extends ContactsGinModule implements EntryPoint {

  private final ContactsGinjector injector = GWT.create(ContactsGinjector.class);

  public void onModuleLoad() {

    AppController appController = new AppController(injector);
    appController.go(RootPanel.get());
  }
}
