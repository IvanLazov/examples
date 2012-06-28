package com.clouway.gwt.requestfactory.contacts.client.editcontact.editor;

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
public class EditContactEditor extends Composite implements Editor<PersonProxy> {
  interface EditContactEditorUiBinder extends UiBinder<Widget, EditContactEditor> {}
  private static EditContactEditorUiBinder uiBinder = GWT.create(EditContactEditorUiBinder.class);

  @UiField
  TextBox firstname;

  @UiField
  TextBox lastname;

  @UiField
  IntegerBox age;

  public EditContactEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}