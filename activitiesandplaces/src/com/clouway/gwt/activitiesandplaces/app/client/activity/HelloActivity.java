package com.clouway.gwt.activitiesandplaces.app.client.activity;

import com.clouway.gwt.activitiesandplaces.app.client.AppFactory;
import com.clouway.gwt.activitiesandplaces.app.client.places.HelloPlace;
import com.clouway.gwt.activitiesandplaces.app.client.ui.HelloView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class HelloActivity extends AbstractActivity implements HelloView.Presenter {

  private AppFactory appFactory;
  private String name;

  public HelloActivity(HelloPlace helloPlace, AppFactory appFactory) {
    //TODO: this.name = helloPlace.getName();
    this.appFactory = appFactory;
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    HelloView helloView = appFactory.getHelloView();
    helloView.setName(name);
    helloView.setPresenter(this);
    panel.setWidget((IsWidget) helloView);
  }

  public void goTo(Place place) {
    appFactory.getPlaceController().goTo(place);
  }

  public String mayStop() {
    return null;
  }
}
