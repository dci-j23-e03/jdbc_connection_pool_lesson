package org.example;

import java.sql.Connection;

public interface ConnectionPool {
  Connection getConnection();
  boolean releaseConnection(Connection connection);
  String getUrl();
  String getUsername();
  String getPassword();
}
