package com.clouway.gwt.requestfactory.contacts.client.addcontact.editor;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactEditor extends Composite implements Editor<PersonProxy>{
  interface AddContactEditorUiBinder extends UiBinder<Widget, AddContactEditor> {}

  private static AddContactEditorUiBinder uiBinder = GWT.create(AddContactEditorUiBinder.class);

  @UiField
  TextBox firstname;

  @UiField
  TextBox lastname;

  @UiField
  IntegerBox age;

  public void clearInputFields() {
    firstname.setText("");
    lastname.setText("");
    age.setText("");
  }

  public AddContactEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}