package com.clouway.gwt.requestfactory.contacts.client.addcontact.activity;

import com.clouway.gwt.requestfactory.contacts.client.ApplicationFactory;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.presenter.AddContactPresenterImpl;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactView;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactViewImpl;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactActivity extends AbstractActivity {

  private ContactsRequestFactory requestFactory;
  private ApplicationFactory applicationFactory;

  public AddContactActivity(ApplicationFactory applicationFactory) {
    this.applicationFactory = applicationFactory;
    this.requestFactory = applicationFactory.getContactsRequestFactory();
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    AddContactView view = new AddContactViewImpl(applicationFactory.getPlaceController());
    AddContactView.Presenter presenter = new AddContactPresenterImpl(view, requestFactory);

    view.setPresenter(presenter);
    panel.setWidget((IsWidget) view);
  }
}
