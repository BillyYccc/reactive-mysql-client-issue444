package com.billyyccc;

import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;

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
                } else {
                    ar.cause().printStackTrace();
                }
                conn.close();
            });
        });

    }
}
