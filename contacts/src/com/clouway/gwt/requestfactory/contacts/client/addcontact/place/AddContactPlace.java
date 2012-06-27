package com.clouway.gwt.requestfactory.contacts.client.addcontact.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddContactPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<AddContactPlace> {

    public AddContactPlace getPlace(String token) {
      return new AddContactPlace();
    }

    public String getToken(AddContactPlace place) {
      return "addContact";
    }
  }
}
