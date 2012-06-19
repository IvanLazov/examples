package com.clouway.requestfactory.sample;

import com.clouway.requestfactory.sample.client.PizzaManager;
import com.clouway.requestfactory.sample.client.PizzaProxy;
import com.clouway.requestfactory.sample.client.PizzaRequestFactory;
import com.clouway.requestfactory.sample.server.PizzaDao;
import com.clouway.requestfactory.sample.server.domain.Ingredient;
import com.clouway.requestfactory.sample.server.domain.Pizza;
import com.google.web.bindery.requestfactory.shared.Receiver;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class PizzaManagerTest {

  private PizzaRequestFactory requestFactory;
  private PizzaDao dao;
  private PizzaManager pizzaManager;

  @Before
  public void setUp() {
    requestFactory = RequestFactoryHelper.create(PizzaRequestFactory.class);
    dao = RequestFactoryHelper.getService(PizzaDao.class);

    pizzaManager = new PizzaManager(requestFactory);
  }

  @Test
  public void findPizzaById() {

    String name = "Funghi";
    Pizza expectedPizza = createPizza(name, Collections.singletonList(new Ingredient()));
    Long id = (long) 5;

    when(dao.findById(id)).thenReturn(expectedPizza);

    Receiver<PizzaProxy> receiver = mock(Receiver.class);
    pizzaManager.findById(id, receiver);

    // Get returned GWT entity proxy
    PizzaProxy returnedPizza = RequestFactoryHelper.captureResult(receiver);

    assertEquals(name, returnedPizza.getName());
    assertEquals(1, returnedPizza.getIngredients().size());
  }

  private Pizza createPizza(String name, List<Ingredient> ingredients) {
    Pizza expectedPizza = new Pizza();
    expectedPizza.setName(name);
    expectedPizza.setIngredients(ingredients);
    return expectedPizza;
  }
}
