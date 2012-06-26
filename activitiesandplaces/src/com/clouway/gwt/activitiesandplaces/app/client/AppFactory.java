package com.clouway.gwt.activitiesandplaces.app.client;

import com.clouway.gwt.activitiesandplaces.app.client.ui.GoodbyeView;
import com.clouway.gwt.activitiesandplaces.app.client.ui.HelloView;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface AppFactory {

  EventBus getEventBus();
  PlaceController getPlaceController();
  HelloView getHelloView();
  GoodbyeView getGoodbyeView();
}
