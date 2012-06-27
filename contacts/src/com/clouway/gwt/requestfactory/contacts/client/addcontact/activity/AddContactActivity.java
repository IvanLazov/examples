package com.clouway.gwt.requestfactory.contacts.client.addcontact.activity;

import com.clouway.gwt.requestfactory.contacts.client.ApplicationFactory;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.presenter.AddContactPresenter;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactView;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactViewImpl;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactActivity extends AbstractActivity {

  private ApplicationFactory applicationFactory;

  public AddContactActivity(ApplicationFactory applicationFactory) {
    this.applicationFactory = applicationFactory;
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    AddContactView addContactView = new AddContactViewImpl(applicationFactory);
    addContactView.setPresenter(new AddContactPresenter(addContactView));

    panel.setWidget((IsWidget) addContactView);
  }
}
