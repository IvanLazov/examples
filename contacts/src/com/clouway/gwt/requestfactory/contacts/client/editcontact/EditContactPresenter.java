package com.clouway.gwt.requestfactory.contacts.client.editcontact;

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
public class EditContactPresenter implements Presenter, EditContactView.Presenter {

  private final ContactsRequestFactory requestFactory;
  private final EditContactView view;

  private RequestFactoryEditorDriver driver;

  private PersonProxy personProxy;
  private PersonProxy editedPersonProxy;
  private PersonRequest personRequest;

  public EditContactPresenter(ContactsRequestFactory requestFactory, EditContactView view, PersonProxy personProxy) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.view.setPresenter(this);

    this.personProxy = personProxy;
    setUpDriver();
  }

  public void go(HasWidgets container) {
    container.add((Widget) view);
  }

  private void setUpDriver() {

    personRequest = requestFactory.personRequest();

    driver = view.getDriver();
    driver.edit(personProxy, personRequest);
  }

  public void save() {

    PersonRequest request = (PersonRequest) driver.flush();
    editedPersonProxy = request.edit(personProxy);

    request.update(personProxy).fire(new Receiver<Void>() {
      public void onSuccess(Void aVoid) {
        driver.edit(editedPersonProxy, requestFactory.personRequest());
        view.showNotificationWindow();
      }
    });
  }
}
