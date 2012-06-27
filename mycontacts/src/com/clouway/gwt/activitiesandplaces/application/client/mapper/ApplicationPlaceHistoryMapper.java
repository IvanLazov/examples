package com.clouway.gwt.activitiesandplaces.application.client.mapper;

import com.clouway.gwt.activitiesandplaces.application.client.place.EditContactPlace;
import com.clouway.gwt.activitiesandplaces.application.client.place.ViewContactsPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@WithTokenizers({ViewContactsPlace.Tokenizer.class, EditContactPlace.Tokenizer.class})
public interface ApplicationPlaceHistoryMapper extends PlaceHistoryMapper {
}
