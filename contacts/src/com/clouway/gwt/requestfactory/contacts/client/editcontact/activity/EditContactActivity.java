package com.clouway.gwt.requestfactory.contacts.client.editcontact.activity;

import com.clouway.gwt.requestfactory.contacts.client.editcontact.presenter.EditContactPresenter;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.ui.EditContactView;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EditContactActivity extends AbstractActivity {

  @Inject
  EditContactView view;

  @Inject
  ContactsRequestFactory requestFactory;

  @Inject
  EditContactPresenter presenter;

  private String personId;

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    presenter.editPerson(personId);

    panel.setWidget((IsWidget) view);
  }
}
