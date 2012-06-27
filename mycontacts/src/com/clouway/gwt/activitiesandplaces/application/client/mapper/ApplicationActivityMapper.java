package com.clouway.gwt.activitiesandplaces.application.client.mapper;

import com.clouway.gwt.activitiesandplaces.application.client.ApplicationFactory;
import com.clouway.gwt.activitiesandplaces.application.client.activity.EditContactActivity;
import com.clouway.gwt.activitiesandplaces.application.client.activity.ViewContactActivity;
import com.clouway.gwt.activitiesandplaces.application.client.place.EditContactPlace;
import com.clouway.gwt.activitiesandplaces.application.client.place.ViewContactsPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ApplicationActivityMapper implements ActivityMapper {

  private ApplicationFactory applicationFactory;

  public ApplicationActivityMapper(ApplicationFactory applicationFactory) {
    this.applicationFactory = applicationFactory;
  }

  public Activity getActivity(Place place) {

    if (place instanceof EditContactPlace) {
      return new EditContactActivity((EditContactPlace) place, applicationFactory);
    }

    if (place instanceof ViewContactsPlace) {
      return new ViewContactActivity(applicationFactory);
    }

    return null;
  }
}
