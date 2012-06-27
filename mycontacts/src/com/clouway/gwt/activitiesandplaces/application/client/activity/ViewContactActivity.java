package com.clouway.gwt.activitiesandplaces.application.client.activity;

import com.clouway.gwt.activitiesandplaces.application.client.ApplicationFactory;
import com.clouway.gwt.activitiesandplaces.application.client.ui.ViewContactPanel;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ViewContactActivity extends AbstractActivity {

  private ApplicationFactory applicationFactory;

  public ViewContactActivity(ApplicationFactory applicationFactory) {
    this.applicationFactory = applicationFactory;
  }

  public void start(AcceptsOneWidget container, EventBus eventBus) {

    ViewContactPanel viewContactPanel = applicationFactory.getViewContactPanel();
    viewContactPanel.setApplicationFactory(applicationFactory);
    viewContactPanel.fillTable();
    container.setWidget(viewContactPanel);
  }
}
