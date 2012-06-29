package com.clouway.gwt.requestfactory.contacts.client.addcontact.presenter;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactView;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactPresenterImpl implements AddContactView.Presenter {

  private AddContactView view;
  private ContactsRequestFactory requestFactory;

  private PersonRequest request;
  private PersonProxy person;

  @Inject
  public AddContactPresenterImpl(AddContactView view, ContactsRequestFactory requestFactory) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.view.setPresenter(this);
  }

  public PersonRequest getPersonRequest() {
    request = requestFactory.personRequest();
    return request;
  }

  public PersonProxy getPersonProxy() {
    person = request.create(PersonProxy.class);
    return person;
  }

  public void save() {

    request.save(person).fire(new Receiver<Void>() {
      public void onSuccess(Void aVoid) {
        view.goToViewContactsPlace();
      }
    });
  }
}
