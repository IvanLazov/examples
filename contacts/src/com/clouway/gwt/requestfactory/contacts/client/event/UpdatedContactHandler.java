package com.clouway.gwt.requestfactory.contacts.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface UpdatedContactHandler extends EventHandler {
  void onUpdatedContact(UpdatedContactEvent event);
}
