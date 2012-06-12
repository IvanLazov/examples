package com.clouway.gwt.bank.server;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class TransactionInterceptor implements MethodInterceptor {

  @Inject
  private Connection connection;

  public Object invoke(MethodInvocation invocation) throws Throwable {

    connection.setAutoCommit(false);

    Object object = null;

    try {
      object = invocation.proceed();
    } catch (SQLException exception) {
      connection.rollback();
    }

    connection.setAutoCommit(true);
    return object;
  }
}
