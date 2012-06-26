package com.clouway.gwt.activitiesandplaces.app.client.ui;

import com.google.gwt.place.shared.Place;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface HelloView {

  void setName(String name);
  void setPresenter(Presenter presenter);

  public interface Presenter {
    void goTo(Place place);
  }
}
