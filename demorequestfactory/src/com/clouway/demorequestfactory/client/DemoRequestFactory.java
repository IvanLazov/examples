package com.clouway.demorequestfactory.client;

import com.clouway.demorequestfactory.client.namelist.NameList;
import com.clouway.demorequestfactory.client.requestfactory.ApplicationRequestFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

public class DemoRequestFactory implements EntryPoint {

  public void onModuleLoad() {
    final EventBus eventBus = new SimpleEventBus();

    ApplicationRequestFactory requestFactory = GWT.create(ApplicationRequestFactory.class);

    requestFactory.initialize(eventBus);

    ClientPersistence clientPersistence = new ClientPersistence(eventBus, requestFactory);

    NameList nameList = new NameList(clientPersistence);
    RootPanel.get().add(nameList);

    nameList.draw();
  }
}
