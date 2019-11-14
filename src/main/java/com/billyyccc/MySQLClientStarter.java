package com.billyyccc;

import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;

import java.sql.*;

public class MySQLClientStarter {
    public static void main(String[] args) {
      MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setHost("localhost")
                .setPort(3306)
                .setUser("root")
                .setPassword("password")
                .setDatabase("mysql")
                .setCharset("utf8");
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(8);
        MySQLPool client = MySQLPool.pool(connectOptions, poolOptions);
        client.getConnection(ar1 -> {
            SqlConnection conn = ar1.result();
            Tuple tuple = Tuple.tuple();
            tuple.addString("اعلن مطوري فيجول نوفل World End Economica");
            tuple.addString("الإعلان عن مشروع World End Economica");
            tuple.addString("this is a story");

            conn.preparedQuery("INSERT INTO news (Title, Summery, Story) VALUES (?, ?, ?)",tuple, ar -> {
                if (ar.succeeded()) {
                    System.out.println("SUCCESS");
                    // jdbc retrieving after inserting with reactive-mysql-client
                  try {
                    Connection jdbcConn =
                      DriverManager.getConnection("jdbc:mysql://localhost/mysql?" +
                        "user=root&password=password&useSSL=false&useServerPrepStmts=TRUE&useUnicode=true&characterEncoding=utf-8");

                    String sql = "SELECT * FROM news WHERE story = 'this is a story';";
                    ResultSet resultSet = jdbcConn.createStatement().executeQuery(sql);
                    while (resultSet.next()) {
                      String title = resultSet.getString("Title");
                      String summery = resultSet.getString("Summery");
                      System.out.println("title: [" + title + "]");
                      System.out.println("summery: [" + summery + "]");
                      System.out.println(title.equals("اعلن مطوري فيجول نوفل World End Economica"));
                      System.out.println(summery.equals("الإعلان عن مشروع World End Economica"));
                    }

                  } catch (SQLException ex) {
                    // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                  }
                } else {
                    ar.cause().printStackTrace();
                }
                conn.close();
            });
        });

    }
}
