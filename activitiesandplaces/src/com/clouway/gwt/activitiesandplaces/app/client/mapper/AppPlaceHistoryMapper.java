package com.clouway.gwt.activitiesandplaces.app.client.mapper;

import com.clouway.gwt.activitiesandplaces.app.client.places.GoodbyePlace;
import com.clouway.gwt.activitiesandplaces.app.client.places.HelloPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;


/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@WithTokenizers({GoodbyePlace.Tokenizer.class, HelloPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
