package com.clouway.gwt.requestfactory.contacts.client.editcontact.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EditContactPlace extends Place {

  private String personId;

  public EditContactPlace(String personId) {
    this.personId = personId;
  }

  public String getPersonId() {
    return this.personId;
  }

  public static class Tokenizer implements PlaceTokenizer<EditContactPlace> {

    public EditContactPlace getPlace(String token) {
      return new EditContactPlace(token);
    }

    public String getToken(EditContactPlace place) {
      return place.getPersonId();
    }
  }
}
