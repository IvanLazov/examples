package com.clouway.gwt.editorframework.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

  public void onModuleLoad() {

    PersonView view = new PersonView();
    RootPanel.get().add(view);
  }
}
