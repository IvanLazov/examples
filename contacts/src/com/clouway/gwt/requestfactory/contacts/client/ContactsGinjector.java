package com.clouway.gwt.requestfactory.contacts.client;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@GinModules(ContactsGinModule.class)
public interface ContactsGinjector extends Ginjector {

  PlaceController injectPlaceController();

  EventBus injectEventBus();

  ActivityMapper injectActivityMapper();
}
