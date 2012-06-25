package com.clouway.gwt.requestfactory.contacts.client.event;

import com.clouway.gwt.requestfactory.contacts.shared.PersonProxy;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class UpdatedContactEvent extends GwtEvent<UpdatedContactHandler> {
  public static Type<UpdatedContactHandler> TYPE = new Type<UpdatedContactHandler>();
  private final PersonProxy personProxy;

  public UpdatedContactEvent(PersonProxy personProxy) {
    this.personProxy = personProxy;
  }

  public Type<UpdatedContactHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(UpdatedContactHandler handler) {
    handler.onUpdatedContact(this);
  }

  public PersonProxy getPersonProxy() {
    return personProxy;
  }
}
