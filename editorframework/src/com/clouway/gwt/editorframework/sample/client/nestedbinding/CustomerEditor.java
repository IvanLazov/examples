package com.clouway.gwt.editorframework.sample.client.nestedbinding;

import com.clouway.gwt.editorframework.sample.client.singlebinding.EmailEditor;
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
public class CustomerEditor extends Composite implements Editor<Customer>{

  interface CustomerEditorUiBinder extends UiBinder<Widget, CustomerEditor> {}
  private static CustomerEditorUiBinder uiBinder = GWT.create(CustomerEditorUiBinder.class);

  @UiField
  TextBox firstName;

  @UiField
  TextBox lastName;

  @UiField
  EmailEditor emailEditor;

  public CustomerEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}