package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
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
    bind(AddContactView.class).to(AddContactViewImpl.class);
    bind(ViewContacts.class).to(ViewContactsImpl.class);
  }

  @Provides @Singleton
  ContactsRequestFactory getContactRequestFactory(EventBus eventBus) {
    ContactsRequestFactory requestFactory = GWT.create(ContactsRequestFactory.class);
    requestFactory.initialize(eventBus);
    return requestFactory;
  }
}
