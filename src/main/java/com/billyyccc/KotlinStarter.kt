package com.billyyccc

import io.vertx.mysqlclient.MySQLConnectOptions
import io.vertx.mysqlclient.MySQLPool
import io.vertx.sqlclient.PoolOptions
import io.vertx.sqlclient.Tuple

fun main() {
  val connectOptions = MySQLConnectOptions()
    .setHost("localhost")
    .setPort(3306)
    .setUser("root")
    .setPassword("password")
    .setDatabase("mysql")
    .setCharset("utf8")
  val poolOptions = PoolOptions()
    .setMaxSize(8)
  val client = MySQLPool.pool(connectOptions, poolOptions)
  client.getConnection { ar1 ->
    val conn = ar1.result()
    val tuple = Tuple.tuple()
    tuple.addString("اعلن مطوري فيجول نوفل World End Economica")
    tuple.addString("الإعلان عن مشروع World End Economica")
    tuple.addString("this is a story")

    conn.preparedQuery("INSERT INTO news (Title, Summery, Story) VALUES (?, ?, ?)", tuple) { ar ->
      if (ar.succeeded()) {
        println("SUCCESS")
      } else {
        ar.cause().printStackTrace()
      }
      conn.close()
    }
  }
}
