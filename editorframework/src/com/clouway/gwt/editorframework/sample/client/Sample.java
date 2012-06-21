package com.clouway.gwt.editorframework.sample.client;

import com.clouway.gwt.editorframework.sample.client.nestedbinding.Customer;
import com.clouway.gwt.editorframework.sample.client.nestedbinding.CustomerEditor;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

  interface Driver extends SimpleBeanEditorDriver<Customer, CustomerEditor>{}
  Driver driver = GWT.create(Driver.class);

  public void onModuleLoad() {
    CustomerEditor customerEditor = new CustomerEditor();

    driver.initialize(customerEditor);

    Customer customer = new Customer();
    driver.edit(customer);

    RootPanel.get().add(customerEditor);

    Button button = new Button("Get Data");
    RootPanel.get().add(button);

    button.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Customer newCustomer = driver.flush();
        Window.alert("Firstname: " + newCustomer.getFirstName() + ", Lastname: " + newCustomer.getLastName()
                                   + ", email: " + newCustomer.getEmail());
      }
    });
  }
}
