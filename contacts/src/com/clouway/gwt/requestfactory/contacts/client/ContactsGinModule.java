package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactView;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactViewImpl;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.ui.EditContactView;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.ui.EditContactViewImpl;
import com.clouway.gwt.requestfactory.contacts.client.mapper.ApplicationActivityMapper;
import com.clouway.gwt.requestfactory.contacts.client.mapper.ApplicationPlaceHistoryMapper;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.ui.ViewContacts;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.ui.ViewContactsImpl;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ContactsGinModule extends AbstractGinModule {

  protected void configure() {

    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

    bind(AddContactView.class).to(AddContactViewImpl.class).in(Singleton.class);

    bind(ViewContacts.class).to(ViewContactsImpl.class).in(Singleton.class);

    bind(EditContactView.class).to(EditContactViewImpl.class).in(Singleton.class);

    bind(PlaceHistoryMapper.class).to(ApplicationPlaceHistoryMapper.class).in(Singleton.class);

    bind(ActivityMapper.class).to(ApplicationActivityMapper.class).in(Singleton.class);
  }

  @Provides @Singleton
  PlaceController getPlaceController(EventBus eventBus) {
    return new PlaceController(eventBus);
  }

  @Provides @Singleton
  ContactsRequestFactory getContactRequestFactory(EventBus eventBus) {
    ContactsRequestFactory requestFactory = GWT.create(ContactsRequestFactory.class);
    requestFactory.initialize(eventBus);
    return requestFactory;
  }
}
