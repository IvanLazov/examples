package com.clouway.gwt.requestfactory.contacts.client.addcontact.ui;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.editor.AddContactEditor;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.place.AddContactPlace;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.place.ViewContactsPlace;
import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactViewImpl extends Composite implements AddContactView {

  interface AddContactViewImplUiBinder extends UiBinder<Widget, AddContactViewImpl> {}
  private static AddContactViewImplUiBinder uiBinder = GWT.create(AddContactViewImplUiBinder.class);

  private Presenter presenter;
  private PlaceController placeController;

  @UiField
  Button view;

  @UiField
  AddContactEditor editor;

  @UiField
  Button save;

  interface Driver extends RequestFactoryEditorDriver<PersonProxy, AddContactEditor>{}
  private final Driver driver = GWT.create(Driver.class);

  public AddContactViewImpl(PlaceController placeController) {

    this.placeController = placeController;

    initWidget(uiBinder.createAndBindUi(this));

    driver.initialize(editor);
  }

  public void setPresenter(Presenter presenter) {

    this.presenter = presenter;

    driver.edit(presenter.getPersonProxy(), presenter.getPersonRequest());
  }

  @UiHandler("save")
  public void onButtonSaveClick(ClickEvent event) {

    if (presenter != null) {

      driver.flush();

      presenter.save();
    }
  }

  @UiHandler("view")
  public void onButtonViewClick(ClickEvent event) {
    placeController.goTo(new ViewContactsPlace());
  }

  public void goToAddContactPlace() {
    placeController.goTo(new AddContactPlace());
  }
}