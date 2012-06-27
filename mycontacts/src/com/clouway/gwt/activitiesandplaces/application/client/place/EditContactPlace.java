package com.clouway.gwt.activitiesandplaces.application.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EditContactPlace extends Place {

  private String contactId;

  public EditContactPlace(String contactId) {
    this.contactId = contactId;
  }

  public String getContactId() {
    return contactId;
  }

  public static class Tokenizer implements PlaceTokenizer<EditContactPlace> {

    public EditContactPlace getPlace(String token) {
      return new EditContactPlace(token);
    }

    public String getToken(EditContactPlace place) {
      return place.getContactId();
    }
  }
}
