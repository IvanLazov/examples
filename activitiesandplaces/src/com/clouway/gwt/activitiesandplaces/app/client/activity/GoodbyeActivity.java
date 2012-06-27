package com.clouway.gwt.activitiesandplaces.app.client.activity;

import com.clouway.gwt.activitiesandplaces.app.client.AppFactory;
import com.clouway.gwt.activitiesandplaces.app.client.places.GoodbyePlace;
import com.clouway.gwt.activitiesandplaces.app.client.ui.GoodbyeView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class GoodbyeActivity extends AbstractActivity {

  private AppFactory appFactory;
  private String token;

  public GoodbyeActivity(GoodbyePlace place, AppFactory appFactory) {
    this.token = place.getToken();
    this.appFactory = appFactory;
  }

  public String mayStop() {
    return null;
  }

  /**
   * Start by the ActivityManager
   *
   * @param container
   * @param eventBus
   */
  public void start(AcceptsOneWidget container, EventBus eventBus) {
    GoodbyeView goodbyeView = appFactory.getGoodbyeView();
    goodbyeView.setName(token);
    container.setWidget((IsWidget) goodbyeView);
  }
}
