package com.clouway.demorequestfactory.client.namelist;

import com.clouway.demorequestfactory.client.ClientPersistence;
import com.clouway.demorequestfactory.client.nameinput.NameInput;
import com.clouway.demorequestfactory.client.requestfactory.NameDataProxy;
import com.clouway.demorequestfactory.client.requestfactory.NameDataRequest;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.Iterator;
import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class NameList extends Composite {
  interface NameListUiBinder extends UiBinder<Widget, NameList> {}
  private static NameListUiBinder uiBinder = GWT.create(NameListUiBinder.class);

  private ClientPersistence clientPersistence;

  @UiField
  VerticalPanel pAdd;

  @UiField
  VerticalPanel pList;

  @UiField
  HorizontalPanel pLoading;

  @UiField
  HTML pExist;

  public NameList(ClientPersistence clientPersistence) {
    this.clientPersistence = clientPersistence;
    initWidget(uiBinder.createAndBindUi(this));

    pExist.setVisible(true);
  }

  public void draw() {
    drawAddInput();

    loadNames();
  }

  private void drawAddInput() {
    pAdd.clear();

    NameInput nameInput = new NameInput(clientPersistence);
    pAdd.add(nameInput);

    nameInput.addAddNameHandler(new AddNameHandler() {
      public void onEvent(AddNameEvent event) {
        NameDataProxy newNameData = event.getNameData();
        drawName(newNameData);
      }
    });
  }

  private void loadNames() {
    // Get RequestFactory
    NameDataRequest nameDataRequest = clientPersistence.getRequestFactory().nameDataRequest();

    showLoading(true);

    nameDataRequest.query().fire(new Receiver<List<NameDataProxy>>() {
      public void onSuccess(List<NameDataProxy> nameDataProxies) {
        showLoading(false);
        process(nameDataProxies);
      }

      public void onFailure(ServerFailure error) {
        showLoading(false);
//        super.onFailure(error);
      }
    });
  }

  private void process(List<NameDataProxy> nameDataProxies) {
    pList.clear();

    if (nameDataProxies == null || nameDataProxies.size() == 0) {
      return;
    }

    Iterator<NameDataProxy> iterator = nameDataProxies.iterator();

    while (iterator.hasNext()) {
      NameDataProxy dataProxy = iterator.next();
      drawName(dataProxy);
    }
  }

  private void drawName(NameDataProxy newNameData) {
    if (newNameData == null) {
      return;
    }

    NameItem nameItem = new NameItem(clientPersistence);
    pList.add(nameItem);
    nameItem.setData(newNameData);
    nameItem.draw();
    nameItem.addDeleteNameHandler(new DeleteNameHandler() {
      public void onEvent(DeleteNameEvent event) {
        showExist();
      }
    });

    showExist();
  }

  private void showExist() {
    if (pList.getWidgetCount() > 0) {
      pExist.setVisible(false);
    } else {
      pExist.setVisible(true);
    }
  }

  private void showLoading(boolean b) {
    pLoading.setVisible(b);
  }
}