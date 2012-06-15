package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.client.presenter.Presenter;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactPresenter implements Presenter, AddContactView.Presenter {

  private final ContactsRequestFactory requestFactory;
  private final AddContactView view;

  public AddContactPresenter(ContactsRequestFactory requestFactory, AddContactView view) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.view.setPresenter(this);
  }

  public void go(HasWidgets container) {
    container.add((Widget) view);
  }

  public void save() {

    PersonRequest personRequest = requestFactory.personRequest();

    PersonProxy personProxy = personRequest.create(PersonProxy.class);
    personProxy.setFirstname(view.getFirstname());
    personProxy.setLastname(view.getLastname());
    personProxy.setAge(Integer.valueOf(view.getAge()));

    personRequest.save(personProxy).fire(new Receiver<Void>() {
      public void onSuccess(Void response) {
        clearView();
        Window.alert("Person was successfully added");
      }
    });
  }

  private void clearView() {
    view.clearInputFields();
  }
}
