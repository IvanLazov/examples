package com.clouway.gwt.bank.server;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class InstanceMatcher<T> extends BaseMatcher<T> {

  private T instance;

  public boolean matches(Object item) {
    try {
      instance = (T) item;
      return true;
    } catch (ClassCastException exception) {
      return false;
    }
  }

  public void describeTo(Description description) {
  }

  public T getInstance() {
    return instance;
  }
}
