package com.clouway.demorequestfactory.client.nameinput;

import com.clouway.demorequestfactory.client.ClientPersistence;
import com.clouway.demorequestfactory.client.namelist.AddNameEvent;
import com.clouway.demorequestfactory.client.namelist.AddNameHandler;
import com.clouway.demorequestfactory.client.requestfactory.NameDataProxy;
import com.clouway.demorequestfactory.client.requestfactory.NameDataRequest;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class NameInput extends Composite {
  interface NameInputUiBinder extends UiBinder<Widget, NameInput> {}
  private static NameInputUiBinder uiBinder = GWT.create(NameInputUiBinder.class);

  private HandlerManager handlerManager;
  private ClientPersistence clientPersistence;

  @UiField
  TextBox tbName;

  @UiField
  PushButton bAdd;

  public NameInput(ClientPersistence clientPersistence) {
    this.clientPersistence = clientPersistence;
    initWidget(uiBinder.createAndBindUi(this));

    handlerManager = new HandlerManager(this);
  }

  @UiHandler("bAdd")
  public void onBAddClick(ClickEvent event) {
    add();
  }

  @UiHandler("tbName")
  public void onTbNameKeyUp(KeyUpEvent event) {
    if (KeyCodes.KEY_ENTER == event.getNativeEvent().getKeyCode()) {
      add();
    }
  }

  private void add() {
    String name = tbName.getText().trim();

    if (name.length() == 0) {
      Window.alert("Did you actually put a name in there? Add some characters please.");
      return;
    }

    save(name);
  }

  private void save(String name) {
    // Get RequestFactory
    NameDataRequest nameDataRequest = clientPersistence.getRequestFactory().nameDataRequest();

    // Create new entity
    NameDataProxy newName = nameDataRequest.create(NameDataProxy.class);
    newName.setName(name);

    // Persist the entity
    nameDataRequest.persist().using(newName).fire(new Receiver<NameDataProxy>() {

      public void onSuccess(NameDataProxy nameDataProxy) {
        process(nameDataProxy);
      }

      @Override
      public void onFailure(ServerFailure error) {
//        super.onFailure(error);
        Window.alert("Oops, I couldn't save that name for some reason");
      }
    });
  }

  private void process(NameDataProxy nameDataProxy) {
    tbName.setText("");

    // Save and add it to the list
    fireEvent(new AddNameEvent(nameDataProxy));
  }

  public void fireEvent(GwtEvent<?> event) {
    handlerManager.fireEvent(event);
  }

  public HandlerRegistration addAddNameHandler(AddNameHandler handler) {
    return handlerManager.addHandler(AddNameEvent.TYPE, handler);
  }
}