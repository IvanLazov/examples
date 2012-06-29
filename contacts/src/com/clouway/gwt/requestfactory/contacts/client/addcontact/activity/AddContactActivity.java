package com.clouway.gwt.requestfactory.contacts.client.addcontact.activity;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.presenter.AddContactPresenterImpl;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactView;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactActivity extends AbstractActivity {

  @Inject
  private AddContactView view;

  @Inject
  private ContactsRequestFactory requestFactory;

  @Inject
  AddContactPresenterImpl presenter;

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    panel.setWidget((IsWidget) view);
  }
}
