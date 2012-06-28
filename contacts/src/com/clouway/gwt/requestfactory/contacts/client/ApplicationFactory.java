package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ApplicationFactory {

  ContactsRequestFactory getContactsRequestFactory();
  PlaceController getPlaceController();
  EventBus getEventBus();

  void initContactRequestFactory(EventBus eventBus);
}
