package com.clouway.gwt.activitiesandplaces.app.client.mapper;

import com.clouway.gwt.activitiesandplaces.app.client.AppFactory;
import com.clouway.gwt.activitiesandplaces.app.client.activity.GoodbyeActivity;
import com.clouway.gwt.activitiesandplaces.app.client.activity.HelloActivity;
import com.clouway.gwt.activitiesandplaces.app.client.places.GoodbyePlace;
import com.clouway.gwt.activitiesandplaces.app.client.places.HelloPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AppActivityMapper implements ActivityMapper {

  private AppFactory appFactory;

  public AppActivityMapper(AppFactory appFactory) {
    super();
    this.appFactory = appFactory;
  }

  public Activity getActivity(Place place) {

    if (place instanceof HelloPlace) {
      return new HelloActivity((HelloPlace) place, appFactory);
    }

    if (place instanceof GoodbyePlace) {
      return new GoodbyeActivity((GoodbyePlace) place, appFactory);
    }

    return null;
  }
}
