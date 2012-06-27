package com.clouway.gwt.activitiesandplaces.application.client;

import com.clouway.gwt.activitiesandplaces.application.client.ui.EditContactPanel;
import com.clouway.gwt.activitiesandplaces.application.client.ui.ViewContactPanel;
import com.clouway.gwt.activitiesandplaces.application.shared.ContactRequestFactory;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ApplicationFactory {

  ContactRequestFactory getContactRequestFactory();
  PlaceController getPlaceController();
  EventBus getEventBus();

  EditContactPanel getEditContactPanel();
  ViewContactPanel getViewContactPanel();

  void setSelectedContactId(String id);
  String getSelectedContactId();

  void initializeRequestFactory(EventBus eventBus);
}
