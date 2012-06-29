package com.clouway.gwt.requestfactory.contacts.client.editcontact.presenter;

import com.clouway.gwt.requestfactory.contacts.client.editcontact.ui.EditContactView;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.RequestContext;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EditContactPresenter implements EditContactView.Presenter {

  private EditContactView view;
  private ContactsRequestFactory requestFactory;
  private PersonProxy person;

  @Inject
  public EditContactPresenter(EditContactView view, ContactsRequestFactory requestFactory) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.view.setPresenter(this);
  }

  public void editPerson(String personId) {

    requestFactory.personRequest().findPerson(Long.valueOf(personId)).fire(new Receiver<PersonProxy>() {
      public void onSuccess(PersonProxy personProxy) {
        person = personProxy;

        PersonRequest request = requestFactory.personRequest();
        request.update(person).to(new Receiver<Void>() {
          public void onSuccess(Void aVoid) {
            view.goToViewContactsPlace();
          }
        });

        view.edit(personProxy, request);
      }
    });
  }

  public void onEditedPerson(RequestContext context) {
    context.fire();
  }
}
