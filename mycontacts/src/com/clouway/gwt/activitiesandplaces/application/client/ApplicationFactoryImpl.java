package com.clouway.gwt.activitiesandplaces.application.client;

import com.clouway.gwt.activitiesandplaces.application.client.ui.EditContactPanel;
import com.clouway.gwt.activitiesandplaces.application.client.ui.ViewContactPanel;
import com.clouway.gwt.activitiesandplaces.application.shared.ContactRequestFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ApplicationFactoryImpl implements ApplicationFactory {

  private static final EventBus eventBus = new SimpleEventBus();
  private static final PlaceController placeController = new PlaceController(eventBus);
  private static final ContactRequestFactory contactRequestFactory = GWT.create(ContactRequestFactory.class);
  private static final ViewContactPanel viewContactPanel = new ViewContactPanel();
  private static final EditContactPanel editContactPanel = new EditContactPanel();

  private static String contactId = "";

  public ContactRequestFactory getContactRequestFactory() {
    return contactRequestFactory;
  }

  public PlaceController getPlaceController() {
    return placeController;
  }

  public EventBus getEventBus() {
    return eventBus;
  }

  public EditContactPanel getEditContactPanel() {
    return editContactPanel;
  }

  public ViewContactPanel getViewContactPanel() {
    return viewContactPanel;
  }

  public void setSelectedContactId(String id) {
    contactId = id;
  }

  public String getSelectedContactId() {
    return contactId;
  }

  public void initializeRequestFactory(EventBus eventBus) {
    contactRequestFactory.initialize(eventBus);
  }
}
