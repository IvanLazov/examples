package com.clouway.gwt.bank.client;

import com.clouway.gwt.bank.client.presenter.Presenter;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import java.util.Map;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AppController implements Presenter, ValueChangeHandler<String> {

  private HasWidgets container;
  private final Map<String, Presenter> userPresenters;

  public AppController(Map<String, Presenter> userPresenters) {
    this.userPresenters = userPresenters;
    bind();
  }

  private void bind() {
    History.addValueChangeHandler(this);
  }

  public void go(HasWidgets container) {
    this.container = container;

    if ("".equals(History.getToken())) {
      History.newItem("register");
    } else {
      History.fireCurrentHistoryState();
    }
  }

  public void onValueChange(ValueChangeEvent<String> event) {

    container.clear();
    String token = event.getValue();

    if (token != null) {
     if (userPresenters.get(token) != null) {
       userPresenters.get(token).go(container);
      }
    }
  }

}
