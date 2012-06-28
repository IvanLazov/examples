package com.clouway.gwt.requestfactory.contacts.client.viewcontacts.activity;

import com.clouway.gwt.requestfactory.contacts.client.ApplicationFactory;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.presenter.ViewContactsPresenter;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.ui.ViewContacts;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.ui.ViewContactsImpl;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ViewContactsActivity extends AbstractActivity {

  private ApplicationFactory applicationFactory;

  public ViewContactsActivity(ApplicationFactory applicationFactory) {
    this.applicationFactory = applicationFactory;
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    ViewContacts view = new ViewContactsImpl(applicationFactory.getPlaceController());
    ViewContactsPresenter presenter = new ViewContactsPresenter(view, applicationFactory.getContactsRequestFactory());
    presenter.loadContacts();

    panel.setWidget((IsWidget) view);
  }
}
