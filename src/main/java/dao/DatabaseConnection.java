package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL kết nối SQL Server
    private static final String URL = "jdbc:sqlserver://DESKTOP-GDJGQDK\\SQLEXPRESS:1433;"
                                    + "databaseName=QuanLyTiemGiatUi;"
                                    + "encrypt=true;trustServerCertificate=true;";
    
    // Tài khoản SQL Server
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    // Nạp driver
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không tìm thấy SQL Server JDBC Driver", e);
        }
    }

    // Hàm trả về Connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Test thử kết nối
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println(" Kết nối SQL Server thành công!");
            } else {
                System.out.println(" Kết nối thất bại!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
