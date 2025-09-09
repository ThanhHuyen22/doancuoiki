package dao;

import model.ChiTietDonHang;
import model.DichVu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietDonHangDAO {

    private static final String URL = "jdbc:sqlserver://DESKTOP-L1OKGPV\\SQLEXPRESS2022:1433;"
            + "databaseName=QuanLyTiemGiatUi;"
            + "encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    public List<ChiTietDonHang> layChiTietTheoDonHang(int maDH) {
        List<ChiTietDonHang> dsChiTiet = new ArrayList<>();

        String sql = "SELECT ctdh.MaCT, ctdh.MaDV, ctdh.SoLuong, dv.TenDV, dv.GiaTien, dv.DonViTinh "
                   + "FROM ChiTietDonHang ctdh "
                   + "INNER JOIN DichVu dv ON ctdh.MaDV = dv.MaDV "
                   + "WHERE ctdh.MaDH = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDH);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DichVu dv = new DichVu();
                dv.setMaDV(rs.getInt("MaDV"));
                dv.setTenDV(rs.getString("TenDV"));
                dv.setGiaTien(rs.getDouble("GiaTien"));
                dv.setDonViTinh(rs.getString("DonViTinh"));

                ChiTietDonHang ct = new ChiTietDonHang();
                ct.setMaCT(rs.getInt("MaCT"));
                ct.setMaDH(maDH);
                ct.setDichVu(dv);
                ct.setSoLuong(rs.getInt("SoLuong"));

                dsChiTiet.add(ct);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsChiTiet;
    }
}
