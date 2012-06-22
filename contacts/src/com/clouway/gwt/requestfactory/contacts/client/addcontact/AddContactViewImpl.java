package com.clouway.gwt.requestfactory.contacts.client.addcontact;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
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

  @UiField
  AddContactEditor editor;

  @UiField
  Button save;

  /**
   * Set up the driver
   */
  interface Driver extends RequestFactoryEditorDriver<PersonProxy, AddContactEditor>{}
  private final Driver driver = GWT.create(Driver.class);

  public AddContactViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
    driver.initialize(editor);
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void showWindow() {
    Window.alert("Person was saved!");
  }

  public void clearInputFields() {
    editor.clearInputFields();
  }

  public RequestFactoryEditorDriver getDriver() {
    return this.driver;
  }

  @UiHandler("save")
  public void onButtonSaveClick(ClickEvent event) {
    if (presenter != null) {
      presenter.save();
    }
  }
}