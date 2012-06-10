package com.clouway.gwt.bank.server;

import java.sql.ResultSet;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ResultSetBuilder<T> {
  T build(ResultSet resultSet);
}
