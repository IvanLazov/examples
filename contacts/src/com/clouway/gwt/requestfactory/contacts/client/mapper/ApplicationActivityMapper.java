package com.clouway.gwt.requestfactory.contacts.client.mapper;

import com.clouway.gwt.requestfactory.contacts.client.ApplicationFactory;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.activity.AddContactActivity;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.place.AddContactPlace;
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

    if (place instanceof AddContactPlace) {
      return new AddContactActivity(applicationFactory);
    }

    return null;
  }
}
