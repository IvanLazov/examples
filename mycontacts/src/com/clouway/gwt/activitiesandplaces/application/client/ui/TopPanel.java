package com.clouway.gwt.activitiesandplaces.application.client.ui;

import com.clouway.gwt.activitiesandplaces.application.client.ApplicationFactory;
import com.clouway.gwt.activitiesandplaces.application.client.place.EditContactPlace;
import com.clouway.gwt.activitiesandplaces.application.client.place.ViewContactsPlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class TopPanel extends Composite {
  interface TopPanelUiBinder extends UiBinder<Widget, TopPanel> {}
  private static TopPanelUiBinder uiBinder = GWT.create(TopPanelUiBinder.class);

  private ApplicationFactory applicationFactory;

  @UiField
  Button view;

  @UiField
  Button edit;

  public TopPanel() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setApplicationFactory(ApplicationFactory applicationFactory) {
    this.applicationFactory = applicationFactory;
  }

  @UiHandler("view")
  public void onButtonViewClick(ClickEvent event) {
    if (applicationFactory != null) {
      applicationFactory.getPlaceController().goTo(new ViewContactsPlace());
    }
  }

  @UiHandler("edit")
  public void onButtonEditClick(ClickEvent event) {
    if (applicationFactory != null) {
      applicationFactory.getPlaceController().goTo(new EditContactPlace(applicationFactory.getSelectedContactId()));
    }
  }
}