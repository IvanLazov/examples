package com.clouway.gwt.editorframework.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddressEditor extends Composite implements Editor<Address> {

  interface AddressEditorUiBinder extends UiBinder<Widget, AddressEditor> {}
  private static AddressEditorUiBinder uiBinder = GWT.create(AddressEditorUiBinder.class);

  @UiField
  TextBox street;

  @UiField
  TextBox city;

  public AddressEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}