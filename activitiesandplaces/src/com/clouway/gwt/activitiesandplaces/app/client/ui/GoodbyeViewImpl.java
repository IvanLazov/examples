package com.clouway.gwt.activitiesandplaces.app.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class GoodbyeViewImpl extends Composite implements GoodbyeView {

  interface GoodbyeViewImplUiBinder extends UiBinder<Widget, GoodbyeViewImpl> {}
  private static GoodbyeViewImplUiBinder uiBinder = GWT.create(GoodbyeViewImplUiBinder.class);

  @UiField
  Label name;

  public GoodbyeViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setName(String name) {
    this.name.setText("Goodbye, " + name);
  }
}