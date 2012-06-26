package com.clouway.gwt.activitiesandplaces.app.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class HelloPlace extends Place {

  private String token;

  public HelloPlace(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public static class Tokenizer implements PlaceTokenizer<HelloPlace> {

    public String getToken(HelloPlace place) {
      return place.getToken();
    }

    public HelloPlace getPlace(String token) {
      return new HelloPlace(token);
    }
  }
}
