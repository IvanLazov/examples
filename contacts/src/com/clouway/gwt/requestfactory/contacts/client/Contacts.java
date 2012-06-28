package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.place.AddContactPlace;
import com.clouway.gwt.requestfactory.contacts.client.mapper.ApplicationActivityMapper;
import com.clouway.gwt.requestfactory.contacts.client.mapper.ApplicationPlaceHistoryMapper;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class Contacts extends ContactsGinModule implements EntryPoint {

  //private final ContactsGinjector injector = GWT.create(ContactsGinjector.class);

  private SimplePanel display = new SimplePanel();
  private Place place = new AddContactPlace();

  public void onModuleLoad() {

    /**
     * Set up ApplicationFactory.
     * Get EventBus and PlaceController
     */
    ApplicationFactory applicationFactory = new ApplicationFactoryImpl();
    EventBus eventBus = applicationFactory.getEventBus();
    PlaceController placeController = applicationFactory.getPlaceController();

    /**
     * Init ContactRequestFactory with EventBus
     */
    applicationFactory.initContactRequestFactory(eventBus);

    /**
     * Start ActivityManager to kick-off objects in response to events
     */
    ActivityMapper activityMapper = new ApplicationActivityMapper(applicationFactory);
    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
    activityManager.setDisplay(display);

    /**
     * Start PlaceHistoryHandler to monitors PlaceChangeEvents
     */
    ApplicationPlaceHistoryMapper placeHistoryMapper = GWT.create(ApplicationPlaceHistoryMapper.class);
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(placeHistoryMapper);
    historyHandler.register(placeController, eventBus, place);

    RootPanel.get().add(display);

    historyHandler.handleCurrentHistory();
  }
}
