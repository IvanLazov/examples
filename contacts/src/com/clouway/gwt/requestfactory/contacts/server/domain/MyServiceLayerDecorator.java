package com.clouway.gwt.requestfactory.contacts.server.domain;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.google.web.bindery.requestfactory.shared.RequestContext;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class MyServiceLayerDecorator extends ServiceLayerDecorator {

  private final Validator validator;
  private final Injector injector;

  @Inject
  public MyServiceLayerDecorator(Validator validator, Injector injector) {
    this.validator = validator;
    this.injector = injector;
  }

  @Override
  public <T extends Locator<?, ?>> T createLocator(Class<T> clazz) {
    return injector.getInstance(clazz);
  }

  @Override
  public Object createServiceInstance(Class<? extends RequestContext> requestContext) {
    Class<?> serviceClazz = resolveServiceClass(requestContext);
    return injector.getInstance(serviceClazz);
  }

  @Override
  public <T> Set<ConstraintViolation<T>> validate(T domainObject) {
    return validator.validate(domainObject);
  }
}
