package com.clouway.demorequestfactory.client.namelist;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class DeleteNameEvent extends GwtEvent<DeleteNameHandler> {
  public static Type<DeleteNameHandler> TYPE = new Type<DeleteNameHandler>();

  public Type<DeleteNameHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(DeleteNameHandler handler) {
    handler.onEvent(this);
  }
}
