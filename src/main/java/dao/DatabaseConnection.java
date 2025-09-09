/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Sửa lại URL/USER/PASSWORD theo môi trường của bạn
    private static final String URL
            = "jdbc:sqlserver://DESKTOP-L1OKGPV\\SQLEXPRESS2022:1433;"
        + "databaseName=QuanLyTiemGiatUi;"
        + "encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    static {
        try {
            // SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            // Nếu thiếu driver -> add mssql-jdbc vào project libraries
            throw new RuntimeException("Không tìm thấy SQLServer JDBC Driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
