package com.clouway.gwt.editorframework.sample.client.singlebinding;

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
public class EmailEditor extends Composite implements Editor<Email>{
  interface EmailEditorUiBinder extends UiBinder<Widget, EmailEditor> {
  }

  private static EmailEditorUiBinder uiBinder = GWT.create(EmailEditorUiBinder.class);

  @UiField
  TextBox email;

  public EmailEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}