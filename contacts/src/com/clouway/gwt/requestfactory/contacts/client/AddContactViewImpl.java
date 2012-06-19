package com.clouway.gwt.requestfactory.contacts.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactViewImpl extends Composite implements AddContactView {

  interface AddContactViewImplUiBinder extends UiBinder<Widget, AddContactViewImpl> {}
  private static AddContactViewImplUiBinder uiBinder = GWT.create(AddContactViewImplUiBinder.class);
  private Presenter presenter;

  @UiField
  TextBox firstname;

  @UiField
  TextBox lastname;

  @UiField
  TextBox age;

  @UiField
  Button save;

  @UiField
  Button viewContacts;

  public AddContactViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public String getFirstname() {
    return firstname.getText();
  }

  public String getLastname() {
    return lastname.getText();
  }

  public String getAge() {
    return age.getText();
  }

  public void clearInputFields() {
    firstname.setText("");
    lastname.setText("");
    age.setText("");
  }

  @UiHandler("save")
  public void onSaveButtonClick(ClickEvent event) {
    if (presenter != null) {
      presenter.save();
    }
  }

  @UiHandler("viewContacts")
  public void onViewContactsButtonClick(ClickEvent event) {
    History.newItem("viewContacts");
  }

}