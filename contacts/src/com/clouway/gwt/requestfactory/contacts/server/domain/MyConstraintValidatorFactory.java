package com.clouway.gwt.requestfactory.contacts.server.domain;

import com.google.inject.Injector;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class MyConstraintValidatorFactory implements ConstraintValidatorFactory {

  private final Injector injector;

  public MyConstraintValidatorFactory(Injector injector) {
    this.injector = injector;
  }

  public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> tClass) {
    return injector.getInstance(tClass);
  }
}
