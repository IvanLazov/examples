package com.clouway.gwt.requestfactory.contacts.client.addcontact.ui;

import com.clouway.gwt.requestfactory.contacts.client.ApplicationFactory;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.editor.AddContactEditor;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.place.AddContactPlace;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactViewImpl extends Composite implements AddContactView {

  interface AddContactViewImplUiBinder extends UiBinder<Widget, AddContactViewImpl> {}
  private static AddContactViewImplUiBinder uiBinder = GWT.create(AddContactViewImplUiBinder.class);

  private ApplicationFactory applicationFactory;

  private PersonRequest personRequest;
  private PersonProxy person;

  private Presenter presenter;

  @UiField
  Button view;

  @UiField
  AddContactEditor editor;

  @UiField
  Button save;

  interface Driver extends RequestFactoryEditorDriver<PersonProxy, AddContactEditor>{}
  private final Driver driver = GWT.create(Driver.class);

  public AddContactViewImpl(ApplicationFactory applicationFactory) {

    this.applicationFactory = applicationFactory;

    initWidget(uiBinder.createAndBindUi(this));
    init();
  }

  private void init() {

    driver.initialize(editor);

    personRequest = getContactsRequestFactory().personRequest();
    person = personRequest.create(PersonProxy.class);

    driver.edit(person, personRequest);
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  private ContactsRequestFactory getContactsRequestFactory() {
    return applicationFactory.getContactsRequestFactory();
  }

  //public void setApplicationFactory(ApplicationFactory applicationFactory) {
    //this.applicationFactory = applicationFactory;
  //}

  /*public void showNotificationWindow() {
    Window.alert("Person was successfully added.");
  }*/

  /*public void clearInputFields() {
    editor.clearInputFields();
  }*/

  /*public RequestFactoryEditorDriver getDriver() {
    return this.driver;
  }*/

  @UiHandler("save")
  public void onButtonSaveClick(ClickEvent event) {
    if (presenter != null) {
      presenter.save();
    }
  }

  public void fire() {

    driver.flush();

    personRequest.save(person).fire(new Receiver<Void>() {
      public void onSuccess(Void aVoid) {
        applicationFactory.getPlaceController().goTo(new AddContactPlace());
      }
    });
  }

  /*@UiHandler("view")
  public void onButtonViewClick(ClickEvent event) {
    History.newItem("view");
  }*/
}