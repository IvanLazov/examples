package com.clouway.gwt.activitiesandplaces.application.client;

import com.clouway.gwt.activitiesandplaces.application.client.mapper.ApplicationActivityMapper;
import com.clouway.gwt.activitiesandplaces.application.client.mapper.ApplicationPlaceHistoryMapper;
import com.clouway.gwt.activitiesandplaces.application.client.place.ViewContactsPlace;
import com.clouway.gwt.activitiesandplaces.application.client.ui.Display;
import com.clouway.gwt.activitiesandplaces.application.client.ui.TopPanel;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.EventBus;


public class Application implements EntryPoint {


  private Display display = new Display();

  private TopPanel topPanel = new TopPanel();

  private Place defaultPlace = new ViewContactsPlace();

  public void onModuleLoad() {
    ApplicationFactory applicationFactory = new ApplicationFactoryImpl();
    EventBus eventBus = applicationFactory.getEventBus();
    PlaceController placeController = applicationFactory.getPlaceController();

    applicationFactory.initializeRequestFactory(eventBus);

    // Start ActivityManager
    ActivityMapper activityMapper = new ApplicationActivityMapper(applicationFactory);
    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
    activityManager.setDisplay(display);

    // Start PlaceHistoryHandler
    ApplicationPlaceHistoryMapper applicationPlaceHistoryMapper= GWT.create(ApplicationPlaceHistoryMapper.class);
    PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(applicationPlaceHistoryMapper);
    placeHistoryHandler.register(placeController, eventBus, defaultPlace);

    topPanel.setApplicationFactory(applicationFactory);

    RootPanel.get().add(topPanel);
    RootPanel.get().add(display);

    placeHistoryHandler.handleCurrentHistory();
  }
}
