package com.clouway.gwt.editorframework.sample.client.singlebinding;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.Window;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EmailDriver {
  interface Driver extends SimpleBeanEditorDriver<Email, EmailEditor> {}

  Driver driver = GWT.create(Driver.class);

  void edit(Email p) {
    EmailEditor editor = new EmailEditor();

    // Initialize the driver and edit the given email.
    driver.initialize(editor);
    driver.edit(p);
  }

  void save() {
    Email edited = driver.flush();
    Window.alert("Edited email: " + edited.getEmail());
  }
}
