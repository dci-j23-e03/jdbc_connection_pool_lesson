package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {

  private final String URL;
  private final String USERNAME;
  private final String PASSWORD;
  private static final int POOL_INITIAL_SIZE = 10;
  private List<Connection> connectionPool;
  private List<Connection> usedConnections;

  BasicConnectionPool(String url, String username, String password) throws SQLException {
    this.URL = url;
    this.USERNAME = username;
    this.PASSWORD = password;

    connectionPool = new ArrayList<>(POOL_INITIAL_SIZE);
    usedConnections = new ArrayList<>(POOL_INITIAL_SIZE);
    for (int i = 0; i < POOL_INITIAL_SIZE; i++) {
      Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      connectionPool.add(connection);
    }
  }

  @Override
  public Connection getConnection() {
    Connection connection = connectionPool.getLast();
    usedConnections.add(connection);
    return connection;
  }

  @Override
  public boolean releaseConnection(Connection connection) {
    if (connection != null) {
      usedConnections.remove(connection);
      connectionPool.add(connection);
      return true;
    }
    return false;
  }

  @Override
  public String getUrl() {
    return URL;
  }

  @Override
  public String getUsername() {
    return USERNAME;
  }

  @Override
  public String getPassword() {
    return PASSWORD;
  }
}
