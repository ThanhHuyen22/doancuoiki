package dao;

import model.DichVu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {

    // Kết nối SQL Server
    private String connectionUrl = "jdbc:sqlserver://DESKTOP-L1OKGPV\\SQLEXPRESS2022:1433;"
            + "databaseName=QuanLyTiemGiatUi;"
            + "encrypt=true;trustServerCertificate=true;";
    private String USER = "sa";
    private String PASSWORD = "sa";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không tìm thấy SQLServer JDBC Driver", e);
        }
    }

    // Lấy tất cả dịch vụ
    public List<DichVu> layTatCaDichVu() {
        List<DichVu> list = new ArrayList<>();
        String sql = "SELECT MaDV, TenDV, GiaTien, DonViTinh,HoTen, NgayNhan, NgayTra FROM DichVu";
        try (Connection conn = DriverManager.getConnection(connectionUrl, USER, PASSWORD); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                DichVu dv = new DichVu();
                dv.setMaDV(rs.getInt("MaDV"));
                dv.setTenDV(rs.getString("TenDV"));
                dv.setGiaTien(rs.getDouble("GiaTien"));
                dv.setDonViTinh(rs.getString("DonViTinh"));
                dv.setHoTen(rs.getString("HoTen"));
                dv.setNgayNhan(rs.getDate("NgayNhan"));
                dv.setNgayTra(rs.getDate("NgayTra"));
                list.add(dv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi load dữ liệu dịch vụ!");
        }
        return list;
    }

    // Thêm dịch vụ
    public boolean themDichVu(DichVu dv) {
        String sql = "INSERT INTO DichVu(TenDV, GiaTien, DonViTinh, HoTen, NgayNhan, NgayTra) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(connectionUrl, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dv.getTenDV());
            ps.setDouble(2, dv.getGiaTien());
            ps.setString(3, dv.getDonViTinh());
            ps.setString(4, dv.getHoTen());
            ps.setDate(5, dv.getNgayNhan() != null ? new java.sql.Date(dv.getNgayNhan().getTime()) : null);
            ps.setDate(6, dv.getNgayTra() != null ? new java.sql.Date(dv.getNgayTra().getTime()) : null);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
// Sửa dịch vụ

    public boolean suaDichVu(DichVu dv) {
        String sql = "UPDATE DichVu SET TenDV=?, GiaTien=?, DonViTinh=?, HoTen=?, NgayNhan=?, NgayTra=? WHERE MaDV=?";
        try (Connection con = DriverManager.getConnection(connectionUrl, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dv.getTenDV());
            ps.setDouble(2, dv.getGiaTien());
            ps.setString(3, dv.getDonViTinh());
            ps.setString(4, dv.getHoTen());
            ps.setDate(5, dv.getNgayNhan() != null ? new java.sql.Date(dv.getNgayNhan().getTime()) : null);
            ps.setDate(6, dv.getNgayTra() != null ? new java.sql.Date(dv.getNgayTra().getTime()) : null);
            ps.setInt(7, dv.getMaDV());

            int kq = ps.executeUpdate();
            return kq > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

// Xóa dịch vụ
    public boolean xoaDichVu(int maDV) {
        String sql = "DELETE FROM DichVu WHERE MaDV=?";
        try (Connection con = DriverManager.getConnection(connectionUrl, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maDV);
            int kq = ps.executeUpdate();
            return kq > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
