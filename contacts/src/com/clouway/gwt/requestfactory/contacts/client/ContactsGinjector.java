package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactView;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.ui.ViewContacts;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@GinModules(ContactsGinModule.class)
public interface ContactsGinjector extends Ginjector {

  ContactsRequestFactory injectContactsRequestFactory();
  AddContactView injectAddContactView();
  ViewContacts injectViewContacts();
}
