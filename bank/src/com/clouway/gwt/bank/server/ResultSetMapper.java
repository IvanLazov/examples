package com.clouway.gwt.bank.server;

import java.sql.ResultSet;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ResultSetMapper<T> {
  T map(ResultSet resultSet);
}
