package com.clouway.gwt.requestfactory.contacts.client.mapper;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.activity.AddContactActivity;
import com.clouway.gwt.requestfactory.contacts.client.addcontact.place.AddContactPlace;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.activity.EditContactActivity;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.place.EditContactPlace;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.activity.ViewContactsActivity;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.place.ViewContactsPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ApplicationActivityMapper implements ActivityMapper {

  @Inject
  AddContactActivity addContactActivity;

  @Inject
  ViewContactsActivity viewContactsActivity;

  @Inject
  EditContactActivity editContactActivity;

  public Activity getActivity(Place place) {

    if (place instanceof AddContactPlace) {
      return addContactActivity;
    }

    if (place instanceof ViewContactsPlace) {
      return viewContactsActivity;
    }

    if (place instanceof EditContactPlace) {

      editContactActivity.setPersonId(((EditContactPlace) place).getPersonId());
      return editContactActivity;
    }

    return null;
  }
}
