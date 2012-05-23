package com.clouway.tests;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class SampleTest {

  class A {
    void method(B b) {
      //b.fix(new User(view.getUserEmail(), view.getName()),new AsyncCallback(6));
    }
  }

  interface B {
    void fix(AsyncCallback asyncCallback);
  }

  interface AsyncCallback {
    void onSuccess();
  }

  @Test
  public void sampleTest() {

    Mockery mockery = new JUnit4Mockery();
    final B b = mockery.mock(B.class);

    final InstanceMather<AsyncCallback> instanceMather = new InstanceMather<AsyncCallback>();

    mockery.checking(new Expectations() {{
      //oneOf(b).fix(with(userMatcher), with(instanceMather));
    }});

    A a = new A();
    a.method(b);

    //User sendUser = userMatcher.getInstance();
    AsyncCallback callback = instanceMather.getInstance();
    // servera mi vurna response
    callback.onSuccess();
  }

  class InstanceMather<T> extends BaseMatcher<T> {

    private T instance;

    @Override
    public boolean matches(Object o) {
      instance = (T) o;
      return true;
    }

    @Override
    public void describeTo(Description description) {
    }

    public T getInstance() {
      return instance;
    }
  }
}
