package com.clouway.gwt.requestfactory.contacts.client;

import com.clouway.gwt.requestfactory.contacts.client.presenter.Presenter;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AppController implements Presenter, ValueChangeHandler<String> {

  private HasWidgets container;
  private AddContactView addContactView;
  private ViewContacts viewContacts;

  private final ContactsGinjector injector;

  public AppController(ContactsGinjector injector) {
    this.injector = injector;
    bind();
  }

  private void bind() {
    History.addValueChangeHandler(this);
  }

  public void go(HasWidgets container) {
    this.container = container;

    if ("".equals(History.getToken())) {
      History.newItem("main");
    } else {
      History.fireCurrentHistoryState();
    }
  }

  public void onValueChange(ValueChangeEvent<String> event) {
    container.clear();

    String token = event.getValue();

    if (token.equals("main")) {

      if (addContactView == null) {
        addContactView = injector.injectAddContactView();
      }
      new AddContactPresenter(injector.injectContactsRequestFactory(), addContactView).go(container);
    }

    if (token.equals("viewContacts")) {

      if (viewContacts == null) {
        viewContacts = injector.injectViewContacts();
      }
      new ViewContactsPresenter(injector.injectContactsRequestFactory(), viewContacts).go(container);
    }
  }
}
