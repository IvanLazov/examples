package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ViewContactsImpl extends Composite implements ViewContacts{
  private Presenter presenter;

  interface ViewContactsImplUiBinder extends UiBinder<Widget, ViewContactsImpl> {}
  private static ViewContactsImplUiBinder uiBinder = GWT.create(ViewContactsImplUiBinder.class);

  @UiField
  FlexTable contactsTable;

  public ViewContactsImpl() {
    initWidget(uiBinder.createAndBindUi(this));

    contactsTable.setText(0, 0, "#");
    contactsTable.setText(0, 1, "Firstname");
    contactsTable.setText(0, 2, "Lastname");
    contactsTable.setText(0, 3, "Age");
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void loadContacts(List<PersonProxy> contacts) {

    for (int i = 0; i < contacts.size(); i++) {
      int rowNumber = contactsTable.getRowCount();

      contactsTable.setText(rowNumber + 1, 1, contacts.get(i).getFirstname());
      contactsTable.setText(rowNumber + 1, 2, contacts.get(i).getLastname());
      contactsTable.setText(rowNumber + 1, 3, contacts.get(i).getAge().toString());
    }
  }

  public void clearContacts() {
    contactsTable.getRowCount();
  }
}