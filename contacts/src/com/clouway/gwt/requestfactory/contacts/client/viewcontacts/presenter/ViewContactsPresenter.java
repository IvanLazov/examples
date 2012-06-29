package com.clouway.gwt.requestfactory.contacts.client.viewcontacts.presenter;

import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.ui.ViewContacts;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ViewContactsPresenter implements ViewContacts.Presenter {

  private ViewContacts view;
  private ContactsRequestFactory requestFactory;

  @Inject
  public ViewContactsPresenter(ViewContacts view, ContactsRequestFactory requestFactory) {
    this.view = view;
    this.view.setPresenter(this);
    this.requestFactory = requestFactory;
  }

  public void loadContacts() {

    view.loadingNotification(true);

    requestFactory.personRequest().findAll().fire(new Receiver<List<PersonProxy>>() {
      public void onSuccess(List<PersonProxy> response) {
        view.loadingNotification(false);
        view.loadContacts(response);
      }
    });
  }

  public void deleteContact(final int rowIndex, Long id) {

    requestFactory.personRequest().delete(id).fire(new Receiver<Void>() {
      public void onSuccess(Void aVoid) {
        view.deleteContact(rowIndex);
      }
    });
  }
}
