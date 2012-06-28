package com.clouway.gwt.requestfactory.contacts.client.editcontact.ui;

import com.clouway.gwt.requestfactory.contacts.client.editcontact.editor.EditContactEditor;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.place.ViewContactsPlace;
import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;
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
public class EditContactViewImpl extends Composite implements EditContactView {

  interface EditContactViewImplUiBinder extends UiBinder<Widget, EditContactViewImpl> {}
  private static EditContactViewImplUiBinder uiBinder = GWT.create(EditContactViewImplUiBinder.class);

  private Presenter presenter;
  private PlaceController placeController;

  interface Driver extends RequestFactoryEditorDriver<PersonProxy, EditContactEditor> {}
  private final Driver driver = GWT.create(Driver.class);

  @UiField
  EditContactEditor editor;

  @UiField
  Button save;

  @UiField
  Button close;

  public EditContactViewImpl(PlaceController placeController) {

    this.placeController = placeController;

    initWidget(uiBinder.createAndBindUi(this));

    driver.initialize(editor);
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void edit(PersonProxy personProxy, PersonRequest request) {
    driver.edit(personProxy, request);
  }

  @UiHandler("save")
  public void onButtonSaveClick(ClickEvent event) {
    if (presenter != null) {
      driver.flush();
      presenter.save();
    }
  }

  @UiHandler("close")
  public void onButtonCancelClick(ClickEvent event) {
    goToViewContactsPlace();
  }

  public void goToViewContactsPlace() {
    placeController.goTo(new ViewContactsPlace());
  }
}