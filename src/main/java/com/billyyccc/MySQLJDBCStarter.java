package com.billyyccc;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

public class MySQLJDBCStarter {
  public static void main(String[] args) {
    JsonObject jdbcClientConfig = new JsonObject()
      .put("url", "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useServerPrepStmts=true")
      .put("user", "root")
      .put("password", "password")
      .put("driver_class", "com.mysql.cj.jdbc.Driver")
      .put("max_pool_size", 30);

    JDBCClient jdbcClient = JDBCClient.createNonShared(Vertx.vertx(), jdbcClientConfig);
    JsonArray params = new JsonArray();
    params.add("اعلن مطوري فيجول نوفل World End Economica");
    params.add("الإعلان عن مشروع World End Economica");
    params.add("this is a story");
    jdbcClient.queryWithParams("INSERT INTO news (Title, Summery, Story) VALUES (?, ?, ?)", params, ar -> {
      if (ar.succeeded()) {
        System.out.println("SUCCESS");
      } else {
        ar.cause().printStackTrace();
      }
    });
  }
}
