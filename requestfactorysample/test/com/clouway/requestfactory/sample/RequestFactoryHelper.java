package com.clouway.requestfactory.sample;

import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.server.ServiceLayer;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;
import com.google.web.bindery.requestfactory.server.SimpleRequestProcessor;
import com.google.web.bindery.requestfactory.server.testing.InProcessRequestTransport;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;
import com.google.web.bindery.requestfactory.vm.RequestFactorySource;
import org.mockito.ArgumentCaptor;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class RequestFactoryHelper {

  private static MockServiceLocator serviceLocator = new MockServiceLocator();
  private static ServiceLayer serviceLayer = ServiceLayer.create(new MockServiceDecorator());

  /**
   * Get instances of different ServiceLocators
   */
  private static class MockServiceLocator implements ServiceLocator {
    private final Map<Class<?>, Object> services = new HashMap<Class<?>, Object>();

    public Object getInstance(Class<?> clazz) {
      Object result = services.get(clazz);

      if (result == null) {
        result = mock(clazz);
        services.put(clazz, result);
      }
      return result;
    }
  }

  private static class MockServiceDecorator extends ServiceLayerDecorator {
    @Override
    public <T extends ServiceLocator> T createServiceLocator(Class<T> clazz) {
      return (T) serviceLocator;
    }
  }

  /**
   * Creates a RequestFactory
   */
  public static <T extends RequestFactory> T create(Class<T> requestFactoryClass) {
    SimpleRequestProcessor processor = new SimpleRequestProcessor(serviceLayer);
    T factory = RequestFactorySource.create(requestFactoryClass);
    factory.initialize(new SimpleEventBus(), new InProcessRequestTransport(processor));
    return factory;
  }

  /**
   * Returns the same instance as used by the RequestFactory
   */
  public static <T> T getService(Class<T> serviceClass) {
    return (T) serviceLocator.getInstance(serviceClass);
  }

  /**
   * Returns the value passed to {}@link Receiver#onSuccess(Object)}
   */
  public static <T> T captureResult(Receiver<T> receiver) {
    ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);

    verify(receiver).onSuccess((T) captor.capture());
    return (T) captor.getValue();
  }
}
