package com.clouway.gwt.requestfactory.contacts.client.editcontact;

import com.clouway.gwt.requestfactory.contacts.client.presenter.Presenter;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EditContactPresenter implements Presenter, EditContactView.Presenter {

  private final ContactsRequestFactory requestFactory;
  private final EditContactView view;



  private PersonProxy personProxy;
  private PersonProxy editedPersonProxy;
  private PersonRequest personRequest;

  public EditContactPresenter(ContactsRequestFactory requestFactory, EditContactView view, PersonProxy personProxy) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.view.setPresenter(this);

    this.personProxy = personProxy;
    editPerson();
  }

  public void go(HasWidgets container) {
    container.add((Widget) view);
  }

  private void editPerson() {

    personRequest = requestFactory.personRequest();

    view.edit(personProxy, personRequest);


    personRequest.update(personProxy).fire(new Receiver<Void>() {
      public void onSuccess(Void aVoid) {
        //TODO: driver.edit(editedPersonProxy, requestFactory.personRequest());
        //TODO: view.showNotificationWindow();
      }
    });
  }

  public void save() {

    view.fireRequest();




  }
}
