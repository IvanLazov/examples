package com.clouway.gwt.editorframework.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class PersonEditor extends Composite implements Editor<Person> {

  interface PersonEditorUiBinder extends UiBinder<Widget, PersonEditor> {}
  private static PersonEditorUiBinder uiBinder = GWT.create(PersonEditorUiBinder.class);

  @UiField
  TextBox firstName;

  @UiField
  TextBox lastName;

  @UiField
  AddressEditor address;

  public PersonEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}