package com.clouway.clickhandler.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.Button;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ClickHandlerTest extends GWTTestCase {

  private Button buttton;
  private boolean condition = false;

  public String getModuleName() {
    return "com.clouway.clickhandler.clickhandler";
  }

  protected void gwtSetUp() throws Exception {
    buttton = new Button();
  }

  public void shouldChangeConditionAfterButtonClick() {
    buttton.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        condition = true;
      }
    });

    buttton.click();
    assertTrue(condition);
  }
}
