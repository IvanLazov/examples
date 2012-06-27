package com.clouway.gwt.activitiesandplaces.application.client.ui;

import com.clouway.gwt.activitiesandplaces.application.client.ApplicationFactory;
import com.clouway.gwt.activitiesandplaces.application.client.place.ViewContactsPlace;
import com.clouway.gwt.activitiesandplaces.application.shared.ContactProxy;
import com.clouway.gwt.activitiesandplaces.application.shared.ContactRequest;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EditContactPanel extends Composite {
  interface EditContactViewUiBinder extends UiBinder<Widget, EditContactPanel> {}
  private static EditContactViewUiBinder uiBinder = GWT.create(EditContactViewUiBinder.class);

  private ApplicationFactory applicationFactory;
  private ContactProxy contact;

  @UiField
  TextBox name;

  @UiField
  TextBox address;

  @UiField
  Button save;

  public EditContactPanel() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @UiHandler("save")
  public void onButtonSaveClick(ClickEvent event) {

    ContactRequest request = applicationFactory.getContactRequestFactory().contactRequest();


    ContactProxy editableContact = request.edit(contact);

    editableContact.setName(name.getText());
    editableContact.setAddress(address.getText());

    request.persist(editableContact).using(editableContact).fire(new Receiver<Void>() {

      public void onSuccess(Void response) {
        applicationFactory.getPlaceController().goTo(new ViewContactsPlace());
      }
    });
  }

  public void getContact(String id) {

    if (applicationFactory != null) {
      applicationFactory.getContactRequestFactory().contactRequest().findContact(Long.parseLong(id)).fire(new Receiver<ContactProxy>() {

        public void onSuccess(ContactProxy response) {
          contact = response;

          name.setText(contact.getName());
          address.setText(contact.getAddress());
        }
      });
    }
  }

  public void setApplicationFactory(ApplicationFactory applicationFactory) {
    this.applicationFactory = applicationFactory;
  }
}