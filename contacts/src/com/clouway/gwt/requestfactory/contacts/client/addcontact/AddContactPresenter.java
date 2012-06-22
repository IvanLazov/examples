package com.clouway.gwt.requestfactory.contacts.client.addcontact;

import com.clouway.gwt.requestfactory.contacts.client.presenter.Presenter;
import com.clouway.gwt.requestfactory.contacts.shared.ContactsRequestFactory;
import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.clouway.gwt.requestfactory.contacts.shared.PersonRequest;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactPresenter implements Presenter, AddContactView.Presenter {

  private final ContactsRequestFactory requestFactory;
  private final AddContactView view;

  private RequestFactoryEditorDriver editor;

  private PersonProxy personProxy;
  private PersonRequest personRequest;

  public AddContactPresenter(ContactsRequestFactory requestFactory, AddContactView view) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.view.setPresenter(this);

    createProxyEditor();
  }

  /**
   * Create editor
   */
  private void createProxyEditor() {
    personRequest = requestFactory.personRequest();
    personProxy = personRequest.create(PersonProxy.class);
    editor = view.getDriver();
    editor.edit(personProxy, personRequest);
  }

  public void go(HasWidgets container) {
    container.add((Widget) view);
  }

  public void save() {

    PersonRequest request = (PersonRequest) editor.flush();

    request.save(personProxy).fire(new Receiver<Void>() {
      public void onSuccess(Void aVoid) {
        view.clearInputFields();
        view.showWindow();
      }
    });
  }
}
