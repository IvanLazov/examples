package com.clouway.gwt.requestfactory.contacts.client.viewcontacts.ui;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.place.AddContactPlace;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.place.EditContactPlace;
import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ViewContactsImpl extends Composite implements ViewContacts {

  interface ViewContactsImplUiBinder extends UiBinder<Widget, ViewContactsImpl> {}
  private static ViewContactsImplUiBinder uiBinder = GWT.create(ViewContactsImplUiBinder.class);

  private List<PersonProxy> loadedContacts;
  private Presenter presenter;
  private PlaceController placeController;

  private final Button deleteButton = new Button("X");
  private final Button editButton = new Button("Edit");

  private int clickedRow;

  @UiField
  FlexTable contactsTable;

  @UiField
  HorizontalPanel loadingNotification;

  @UiField
  Button addContact;

  @Inject
  public ViewContactsImpl(PlaceController placeController) {

    this.placeController = placeController;

    initWidget(uiBinder.createAndBindUi(this));

    contactsTable.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {

        clickedRow = contactsTable.getCellForEvent(event).getRowIndex();
        contactsTable.setWidget(clickedRow, 4, deleteButton);
        contactsTable.setWidget(clickedRow, 5, editButton);
      }
    });

    deleteButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        presenter.deleteContact(clickedRow, loadedContacts.get(clickedRow).getId());
      }
    });

    editButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        goToPlace(loadedContacts.get(clickedRow).getId());
      }
    });
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void loadContacts(List<PersonProxy> contacts) {

    contactsTable.removeAllRows();

    loadedContacts = contacts;

    for (PersonProxy contact : contacts) {
      int rowNumber = contactsTable.getRowCount();

      contactsTable.setText(rowNumber, 0, String.valueOf(rowNumber + 1) + ". ");
      contactsTable.setText(rowNumber, 1, contact.getFirstname());
      contactsTable.setText(rowNumber, 2, contact.getLastname());
      contactsTable.setText(rowNumber, 3, contact.getAge().toString());

      contactsTable.getCellFormatter().setWidth(rowNumber, 1, "70");
      contactsTable.getCellFormatter().setWidth(rowNumber, 2, "70");
      contactsTable.getCellFormatter().setWidth(rowNumber, 3, "40");
    }
  }

  @UiHandler("addContact")
  public void onAddContactButtonClick(ClickEvent event) {
    placeController.goTo(new AddContactPlace());
  }

  private void goToPlace(Long id) {
    placeController.goTo(new EditContactPlace(String.valueOf(id)));
  }

  public void deleteContact(int rowIndex) {
    contactsTable.removeRow(rowIndex);
    loadedContacts.remove(rowIndex);
  }

  public void loadingNotification(boolean visible) {
    loadingNotification.setVisible(visible);
  }
}