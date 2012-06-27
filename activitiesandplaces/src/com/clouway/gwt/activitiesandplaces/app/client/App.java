package com.clouway.gwt.activitiesandplaces.app.client;

import com.clouway.gwt.activitiesandplaces.app.client.mapper.AppActivityMapper;
import com.clouway.gwt.activitiesandplaces.app.client.mapper.AppPlaceHistoryMapper;
import com.clouway.gwt.activitiesandplaces.app.client.places.HelloPlace;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class App implements EntryPoint {

  private HelloPlace helloPlace = new HelloPlace("John");
  private SimplePanel display = new SimplePanel();

  public void onModuleLoad() {
    AppFactory appFactory = new AppFactoryImpl();
    EventBus eventBus = appFactory.getEventBus();
    PlaceController placeController = appFactory.getPlaceController();

    // Start ActivityManager with out AppActivityMapper
    ActivityMapper activityMapper = new AppActivityMapper(appFactory);
    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
    activityManager.setDisplay(display);

    // Start PlaceHistoryHandler with out AppPlaceHistoryMapper
    AppPlaceHistoryMapper appPlaceHistoryMapper = GWT.create(AppPlaceHistoryMapper.class);
    PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(appPlaceHistoryMapper);
    placeHistoryHandler.register(placeController, eventBus, helloPlace);

    RootPanel.get().add(display);

    placeHistoryHandler.handleCurrentHistory();
  }
}
