package com.clouway.gwt.requestfactory.contacts.server.domain;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AppLoader extends GuiceServletContextListener {

  protected Injector getInjector() {

    return Guice.createInjector(new ServletModule() {
      @Override
      protected void configureServlets() {
        serve("/gwtRequest").with(MyRequestFactoryServlet.class);

        bind(DatabaseHelper.class).in(Singleton.class);
        bind(PersonRepository.class).to(PersonRepositoryImpl.class);
        bind(PersonService.class).to(PersonServiceImpl.class);
      }

      @Provides @Singleton
      ValidatorFactory getValidatorFactory(Injector injector) {
        return Validation.byDefaultProvider().configure().constraintValidatorFactory(new MyConstraintValidatorFactory(injector)).buildValidatorFactory();
      }

      @Provides @Singleton
      Validator getValidator(ValidatorFactory validatorFactory) {
        return validatorFactory.getValidator();
      }
    });
  }
}
