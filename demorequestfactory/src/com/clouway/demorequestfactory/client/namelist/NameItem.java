package com.clouway.demorequestfactory.client.namelist;

import com.clouway.demorequestfactory.client.ClientPersistence;
import com.clouway.demorequestfactory.client.requestfactory.NameDataProxy;
import com.clouway.demorequestfactory.client.requestfactory.NameDataRequest;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class NameItem extends Composite {
  interface NameItemUiBinder extends UiBinder<Widget, NameItem> {}
  private static NameItemUiBinder uiBinder = GWT.create(NameItemUiBinder.class);

  private ClientPersistence clientPersistence;
  private HandlerManager handlerManager;
  private NameDataProxy nameData;

  @UiField
  HTML htmlName;

  @UiField
  PushButton bDelete;

  public NameItem(ClientPersistence clientPersistence) {
    this.clientPersistence = clientPersistence;
    initWidget(uiBinder.createAndBindUi(this));

    handlerManager = new HandlerManager(this);
  }

  public void setData(NameDataProxy data) {
    this.nameData = data;
  }

  public void draw() {
    drawName();
  }

  private void drawName() {
    String name = nameData.getName();

    String s = "None";
    if (name != null) {
      s = name;
    }

    htmlName.setHTML(SimpleHtmlSanitizer.sanitizeHtml(s));
  }

  @UiHandler("bDelete")
  public void onBDeleteClick(ClickEvent event) {
    delete();
  }

  private void delete() {
    if (nameData == null || nameData.stableId() == null) {
      removeFromParent();
      fireDelete();
      return;
    }

    // Get RequestFactory
    NameDataRequest nameDataRequest = clientPersistence.getRequestFactory().nameDataRequest();

    nameDataRequest.remove().using(nameData).fire(new Receiver<Void>() {

      public void onSuccess(Void aVoid) {
        processDelete();
      }
    });
  }

  private void processDelete() {
    removeFromParent();
    fireDelete();
  }

  private void fireDelete() {
    fireEvent(new DeleteNameEvent());
  }

  public void fireEvent(GwtEvent<?> event) {
    handlerManager.fireEvent(event);
  }

  public HandlerRegistration addDeleteNameHandler(DeleteNameHandler handler) {
    return handlerManager.addHandler(DeleteNameEvent.TYPE, handler);
  }
}