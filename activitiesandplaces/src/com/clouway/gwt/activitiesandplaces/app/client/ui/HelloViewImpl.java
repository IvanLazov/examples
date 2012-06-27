package com.clouway.gwt.activitiesandplaces.app.client.ui;

import com.clouway.gwt.activitiesandplaces.app.client.places.GoodbyePlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class HelloViewImpl extends Composite implements HelloView {

  interface HelloViewImplUiBinder extends UiBinder<Widget, HelloViewImpl> {}
  private static HelloViewImplUiBinder uiBinder = GWT.create(HelloViewImplUiBinder.class);

  @UiField
  Label label;

  @UiField
  Anchor link;

  private Presenter presenter;

  private String name;

  public HelloViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setName(String name) {
    this.name = name;
    label.setText(name);
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @UiHandler("link")
  public void onLinkClick(ClickEvent event) {
    presenter.goTo(new GoodbyePlace(name));
  }
}