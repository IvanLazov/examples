package com.clouway.gwt.requestfactory.contacts.client.mapper;

import com.clouway.gwt.requestfactory.contacts.client.addcontact.place.AddContactPlace;
import com.clouway.gwt.requestfactory.contacts.client.editcontact.place.EditContactPlace;
import com.clouway.gwt.requestfactory.contacts.client.viewcontacts.place.ViewContactsPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@WithTokenizers({AddContactPlace.Tokenizer.class, ViewContactsPlace.Tokenizer.class, EditContactPlace.Tokenizer.class})
public interface ApplicationPlaceHistoryMapper extends PlaceHistoryMapper {
}
