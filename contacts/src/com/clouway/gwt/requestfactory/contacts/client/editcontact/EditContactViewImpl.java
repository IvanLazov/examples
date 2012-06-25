package com.clouway.gwt.requestfactory.contacts.client.editcontact;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EditContactViewImpl extends PopupPanel implements EditContactView {

  interface EditContactViewImplUiBinder extends UiBinder<Widget, EditContactViewImpl> {}
  private static EditContactViewImplUiBinder uiBinder = GWT.create(EditContactViewImplUiBinder.class);

  private Presenter presenter;

  /**
   * Set up the driver
   */
  interface Driver extends RequestFactoryEditorDriver<PersonProxy, EditContactEditor> {}
  private final Driver driver = GWT.create(Driver.class);

  @UiField
  EditContactEditor editor;

  @UiField
  Button save;

  @UiField
  Button close;

  public EditContactViewImpl() {
    super(false);
    this.setPopupPosition(500, 55);
    this.setGlassEnabled(true);
    setWidget(uiBinder.createAndBindUi(this));
    show();

    driver.initialize(editor);
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public RequestFactoryEditorDriver getDriver() {
    return this.driver;
  }

  public void showNotificationWindow() {
    Window.alert("Person successfully edited.");
  }

  @UiHandler("save")
  public void onButtonSaveClick(ClickEvent event) {
    if (presenter != null) {
      presenter.save();
    }
  }

  @UiHandler("close")
  public void onButtonCancelClick(ClickEvent event) {
    this.hide();
  }


}