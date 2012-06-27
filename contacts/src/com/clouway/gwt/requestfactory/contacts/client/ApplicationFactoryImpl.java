package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ApplicationFactoryImpl implements ApplicationFactory {

  private static final ContactsRequestFactory contactsRequestFactory = GWT.create(ContactsRequestFactory.class);
  private static final EventBus eventBus = new SimpleEventBus();
  private static final PlaceController placeController = new PlaceController(eventBus);
  //private static final AddContactView addContactView = new AddContactViewImpl();
  //private static final AddContactPresenter addContactPresenter = new AddContactPresenter(addContactView);

  public ContactsRequestFactory getContactsRequestFactory() {
    return contactsRequestFactory;
  }

  public PlaceController getPlaceController() {
    return placeController;
  }

  public EventBus getEventBus() {
    return eventBus;
  }

  /*public AddContactView getAddContactView() {
    return new AddContactViewImpl();
  }*/

  public void initContactRequestFactory(EventBus eventBus) {
    contactsRequestFactory.initialize(eventBus);
  }

  /*public AddContactPresenter getAddContactPresenter() {
    return addContactPresenter;
  }*/
}
