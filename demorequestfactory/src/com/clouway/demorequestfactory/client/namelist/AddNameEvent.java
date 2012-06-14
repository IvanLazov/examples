package com.clouway.demorequestfactory.client.namelist;

import com.clouway.demorequestfactory.client.requestfactory.NameDataProxy;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AddNameEvent extends GwtEvent<AddNameHandler> {
  public static Type<AddNameHandler> TYPE = new Type<AddNameHandler>();
  private final NameDataProxy nameDataProxy;

  public AddNameEvent(NameDataProxy nameDataProxy) {
    this.nameDataProxy = nameDataProxy;
  }

  public Type<AddNameHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(AddNameHandler handler) {
    handler.onEvent(this);
  }

  public NameDataProxy getNameData() {
    return nameDataProxy;
  }
}
