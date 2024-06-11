package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

  private static final String URL = "jdbc:postgresql://localhost:5432/lesson2";
  private static final String USERNAME = "postgres";
  private static final String PASSWORD = "";

  public static void main(String[] args) {
    ConnectionPool connectionPool;
    try {
      connectionPool = new BasicConnectionPool(URL, USERNAME, PASSWORD);

      Connection connection = connectionPool.getConnection();
      String query = "select properties ->> 'color' color from products;";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      System.out.println("A color saved in db is: " + resultSet.getString(1));
    } catch (SQLException e) {
      System.out.println("Problem occurred in connection pool creation: " + e.getMessage());
    }
  }
}