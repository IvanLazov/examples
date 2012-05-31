package com.clouway.gwt.bank.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

public class Bank implements EntryPoint {

  public void onModuleLoad() {
    BankServiceAsync rpcService = GWT.create(BankService.class);
    EventBus eventBus = new SimpleEventBus();
    AppController controller = new AppController(rpcService, eventBus);
    controller.go(RootPanel.get());
  }
}
