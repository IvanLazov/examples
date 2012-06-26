package com.clouway.gwt.activitiesandplaces.app.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class GoodbyePlace extends Place {

  private String token;

  public GoodbyePlace(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public static class Tokenizer implements PlaceTokenizer<GoodbyePlace> {

    public GoodbyePlace getPlace(String token) {
      return new GoodbyePlace(token);
    }

    @Override
    public String getToken(GoodbyePlace place) {
      return place.getToken();
    }
  }
}
