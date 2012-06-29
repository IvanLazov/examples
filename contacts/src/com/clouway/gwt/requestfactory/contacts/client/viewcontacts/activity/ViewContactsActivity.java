package com.clouway.gwt.requestfactory.contacts.client.viewcontacts.activity;

import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.presenter.ViewContactsPresenter;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.ui.ViewContacts;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ViewContactsActivity extends AbstractActivity {

  @Inject
  ViewContacts view;

  @Inject
  ContactsRequestFactory requestFactory;

  @Inject
  ViewContactsPresenter presenter;

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    presenter.loadContacts();

    panel.setWidget((IsWidget) view);
  }
}
