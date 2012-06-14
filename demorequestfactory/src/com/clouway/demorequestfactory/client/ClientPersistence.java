package com.clouway.demorequestfactory.client;

import com.clouway.demorequestfactory.client.requestfactory.ApplicationRequestFactory;
import com.google.gwt.event.shared.EventBus;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ClientPersistence {

  private EventBus eventBus;
  private ApplicationRequestFactory requestFactory;

  public ClientPersistence(EventBus eventBus, ApplicationRequestFactory requestFactory) {
    this.eventBus = eventBus;
    this.requestFactory = requestFactory;
  }

  public EventBus getEventBus() {
    return eventBus;
  }

  public ApplicationRequestFactory getRequestFactory() {
    return requestFactory;
  }
}
