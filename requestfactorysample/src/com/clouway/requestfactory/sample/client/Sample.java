package com.clouway.requestfactory.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.Arrays;

public class Sample implements EntryPoint {

  public void onModuleLoad() {

    final EventBus eventBus = new SimpleEventBus();
    PizzaRequestFactory requestFactory = GWT.create(PizzaRequestFactory.class);
    requestFactory.initialize(eventBus);

    PizzaRequestFactory.PizzaRequestContext context = requestFactory.context();

    PizzaProxy pizza = context.create(PizzaProxy.class);
    pizza.setName("Funghi");

    IngredientProxy cheese = context.create(IngredientProxy.class);
    cheese.setName("Cheese");
    cheese.setVegan(false);

    pizza.setIngredients(Arrays.asList(cheese));

    context.save(pizza).fire(new Receiver<Void>() {

      public void onSuccess(Void response) {
        System.out.println("Request was successfully sent");
      }
    });
  }

}
