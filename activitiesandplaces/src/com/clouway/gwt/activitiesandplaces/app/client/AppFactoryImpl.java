package com.clouway.gwt.activitiesandplaces.app.client;

import com.clouway.gwt.activitiesandplaces.app.client.ui.GoodbyeView;
import com.clouway.gwt.activitiesandplaces.app.client.ui.GoodbyeViewImpl;
import com.clouway.gwt.activitiesandplaces.app.client.ui.HelloView;
import com.clouway.gwt.activitiesandplaces.app.client.ui.HelloViewImpl;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AppFactoryImpl implements AppFactory {

  private static final EventBus eventBus = new SimpleEventBus();
  private static final PlaceController placeController = new PlaceController(eventBus);
  private static final HelloView helloView = new HelloViewImpl();
  private static final GoodbyeView goodbyeView = new GoodbyeViewImpl();

  public EventBus getEventBus() {
    return eventBus;
  }

  public PlaceController getPlaceController() {
    return placeController;
  }

  public HelloView getHelloView() {
    return helloView;
  }

  public GoodbyeView getGoodbyeView() {
    return goodbyeView;
  }
}
