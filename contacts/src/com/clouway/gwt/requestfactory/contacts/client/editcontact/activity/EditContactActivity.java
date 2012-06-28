package com.clouway.gwt.requestfactory.contacts.client.editcontact.activity;

import com.clouway.gwt.requestfactory.contacts.client.ApplicationFactory;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.place.EditContactPlace;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.presenter.EditContactPresenter;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.ui.EditContactView;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.ui.EditContactViewImpl;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EditContactActivity extends AbstractActivity {

  private ApplicationFactory applicationFactory;
  private EditContactPlace place;

  public EditContactActivity(ApplicationFactory applicationFactory, EditContactPlace place) {

    this.applicationFactory = applicationFactory;
    this.place = place;
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    EditContactView view = new EditContactViewImpl(applicationFactory.getPlaceController());
    EditContactPresenter presenter = new EditContactPresenter(view, applicationFactory.getContactsRequestFactory());
    presenter.find(place.getPersonId());

    panel.setWidget((IsWidget) view);
  }
}
