package com.clouway.gwt.activitiesandplaces.app.client.activity;

import com.clouway.gwt.activitiesandplaces.app.client.AppFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class GoodbyeActivity extends AbstractActivity {

  private AppFactory appFactory;
  private String name;


  public void start(AcceptsOneWidget panel, EventBus eventBus) {

  }

  public String mayStop() {
    return null;
  }
}
