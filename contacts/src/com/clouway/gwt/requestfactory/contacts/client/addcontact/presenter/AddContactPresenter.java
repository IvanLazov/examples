package com.clouway.gwt.requestfactory.contacts.client.addcontact.presenter;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.ui.AddContactView;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactPresenter implements AddContactView.Presenter {

  //private final ContactsRequestFactory requestFactory;

  //private RequestFactoryEditorDriver driver;

  //private PersonProxy personProxy;
  //private PersonRequest personRequest;

  /*public AddContactPresenter(ContactsRequestFactory requestFactory, AddContactView view) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.view.setPresenter(this);

    setUpDriver();
  }*/

  private final AddContactView view;

  public AddContactPresenter(AddContactView view) {
    this.view = view;
    this.view.setPresenter(this);
  }

  /*private void setUpDriver() {

    // TODO:
    personRequest = requestFactory.personRequest();
    personProxy = personRequest.create(PersonProxy.class);
    driver = view.getDriver();
    driver.edit(personProxy, personRequest);
  }*/

  public void save() {

    // TODO:
    /*PersonRequest request = (PersonRequest) driver.flush();

    request.save(personProxy).fire(new Receiver<Void>() {
      public void onSuccess(Void aVoid) {
        view.clearInputFields();
        view.showNotificationWindow();
      }
    });

    setUpDriver();*/
    view.fire();
  }

  /*public void go(HasWidgets container) {
    container.add((Widget) view);
  }*/
}
