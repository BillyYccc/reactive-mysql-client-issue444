package com.billyyccc;

import java.sql.*;

public class JDBCStarter {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/mysql?" +
                            "user=root&password=password&useSSL=false&useServerPrepStmts=TRUE&useUnicode=true&characterEncoding=utf-8");

            String sql = "INSERT INTO news (Title, Summery, Story) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "اعلن مطوري فيجول نوفل World End Economica");
            preparedStatement.setString(2, "الإعلان عن مشروع World End Economica");
            preparedStatement.setString(3, "this is a story");
            boolean result = preparedStatement.execute();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
