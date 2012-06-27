package com.clouway.gwt.activitiesandplaces.application.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ViewContactsPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<ViewContactsPlace> {

    public ViewContactsPlace getPlace(String token) {
      return new ViewContactsPlace();
    }

    public String getToken(ViewContactsPlace place) {
      return "";
    }
  }
}
