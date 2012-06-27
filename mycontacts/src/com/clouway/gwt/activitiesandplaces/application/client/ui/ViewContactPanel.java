package com.clouway.gwt.activitiesandplaces.application.client.ui;

import com.clouway.gwt.activitiesandplaces.application.client.ApplicationFactory;
import com.clouway.gwt.activitiesandplaces.application.shared.ContactProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ViewContactPanel extends Composite {
  interface ViewContactPanelUiBinder extends UiBinder<Widget, ViewContactPanel> {}
  private static ViewContactPanelUiBinder uiBinder = GWT.create(ViewContactPanelUiBinder.class);

  private ApplicationFactory applicationFactory;
  private List<ContactProxy> contacts;

  @UiField(provided = true)
  CellTable<ContactProxy> contactsTable;

  public ViewContactPanel() {
    createContactsTable();
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setApplicationFactory(ApplicationFactory applicationFactory) {
    this.applicationFactory = applicationFactory;
  }

  public void fillTable() {
    if (applicationFactory != null) {
      applicationFactory.getContactRequestFactory().contactRequest().generateContacts().fire(new Receiver<Void>() {
        public void onSuccess(Void response) {
          updateTable();
        }
      });
    }
  }

  private void updateTable() {
    if (applicationFactory != null) {
      applicationFactory.getContactRequestFactory().contactRequest().findAllContact().fire(new Receiver<List<ContactProxy>>() {

        public void onSuccess(List<ContactProxy> response) {
          contacts = response;

          contactsTable.setRowCount(contacts.size(), true);
          contactsTable.setRowData(0, contacts);
        }
      });
    }
  }

  private void createContactsTable() {

    contactsTable = new CellTable<ContactProxy>();
    contactsTable.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);

    // Create column - name
    TextColumn<ContactProxy> nameColumn = new TextColumn<ContactProxy>() {
      public String getValue(ContactProxy contact) {
        return contact.getName();
      }
    };

    // Create column - address
    TextColumn<ContactProxy> addressColumn = new TextColumn<ContactProxy>() {
      public String getValue(ContactProxy contact) {
        return contact.getAddress();
      }
    };

    contactsTable.addColumn(nameColumn, "Name");
    contactsTable.addColumn(addressColumn, "Address");

    // Add selection model to handler clicks
    final SingleSelectionModel<ContactProxy> selectionModel = new SingleSelectionModel<ContactProxy>();
    contactsTable.setSelectionModel(selectionModel);
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

      public void onSelectionChange(SelectionChangeEvent event) {
        ContactProxy selectedContact = selectionModel.getSelectedObject();

        System.out.println(selectedContact);

        if (selectedContact != null) {
          applicationFactory.setSelectedContactId(selectedContact.getId().toString());
        }
      }
    });
  }
}