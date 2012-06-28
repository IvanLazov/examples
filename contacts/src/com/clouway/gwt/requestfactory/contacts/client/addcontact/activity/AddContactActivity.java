package com.clouway.gwt.requestfactory.contacts.client.addcontact.activity;

import com.clouway.gwt.requestfactory.contacts.client.ApplicationFactory;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.presenter.AddContactPresenterImpl;
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

    AddContactView view = new AddContactViewImpl(applicationFactory.getPlaceController());
    AddContactView.Presenter presenter = new AddContactPresenterImpl(view, applicationFactory.getContactsRequestFactory());

    panel.setWidget((IsWidget) view);
  }
}
