package com.clouway.gwt.editorframework.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.editor.client.EditorDriver;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class PersonView extends Composite {

  interface PersonViewUiBinder extends UiBinder<Widget, PersonView> {}
  private static PersonViewUiBinder uiBinder = GWT.create(PersonViewUiBinder.class);

  interface Driver extends SimpleBeanEditorDriver<Person, PersonEditor> {}
  private final Driver driver = GWT.create(Driver.class);

  @UiField
  PersonEditor editor;

  @UiField
  Button save;

  @UiField
  Button cancel;

  public PersonView() {
    initWidget(uiBinder.createAndBindUi(this));
    driver.initialize(editor);
    driver.edit(new Person());
  }

  @UiHandler("save")
  public void onSaveButtonClick(ClickEvent event) {
    Person person = driver.flush();
    Window.alert(
                 "Firstname: " + person.getFirstName()
               + "\nLastName: "  + person.getLastName()
               + "\nCity: " + person.getAddress().getCity()
               + "\nStreet: " + person.getAddress().getStreet());
  }
}