package com.clouway.gwt.activitiesandplaces.application.client.activity;

import com.clouway.gwt.activitiesandplaces.application.client.ApplicationFactory;
import com.clouway.gwt.activitiesandplaces.application.client.place.EditContactPlace;
import com.clouway.gwt.activitiesandplaces.application.client.ui.EditContactPanel;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EditContactActivity extends AbstractActivity {

  private ApplicationFactory applicationFactory;
  private String contactId;

  public EditContactActivity(EditContactPlace place, ApplicationFactory applicationFactory) {
    contactId = place.getContactId();
    this.applicationFactory = applicationFactory;
  }

  public void start(AcceptsOneWidget container, EventBus eventBus) {

    EditContactPanel editContactPanel = applicationFactory.getEditContactPanel();
    editContactPanel.setApplicationFactory(applicationFactory);
    editContactPanel.getContact(contactId);
    container.setWidget(editContactPanel.asWidget());
  }
}
