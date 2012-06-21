package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ViewContactsImpl extends Composite implements ViewContacts {

  interface ViewContactsImplUiBinder extends UiBinder<Widget, ViewContactsImpl> {}
  private static ViewContactsImplUiBinder uiBinder = GWT.create(ViewContactsImplUiBinder.class);

  private Presenter presenter;
  private final Button deleteButton = new Button("X");
  private List<PersonProxy> loadedContacts;

  @UiField
  FlexTable contactsTable;

  @UiField
  HorizontalPanel loadingNotification;

  @UiField
  Button addContact;

  public ViewContactsImpl() {

    initWidget(uiBinder.createAndBindUi(this));

    contactsTable.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        int row = contactsTable.getCellForEvent(event).getRowIndex();
        contactsTable.setWidget(row, 4, deleteButton);
      }
    });

    deleteButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        int rowIndex = contactsTable.getCellForEvent(event).getRowIndex();
        presenter.deleteContact(rowIndex, loadedContacts.get(rowIndex).getId());
      }
    });
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void loadContacts(List<PersonProxy> contacts) {

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
    History.newItem("main");
  }

  public void clearContacts() {
    contactsTable.removeAllRows();
  }

  public void deleteContact(int rowIndex) {
    contactsTable.removeRow(rowIndex);
  }

  public void loadingNotification(boolean visible) {
    loadingNotification.setVisible(visible);
  }
}