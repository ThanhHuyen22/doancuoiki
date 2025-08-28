/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.DonHang;
import model.KhachHang;
import model.ChiTietDonHang;
import model.DichVu;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonHangDAO {

    private List<DonHang> danhSachDonHang = new ArrayList<>();
    private int nextMaDH = 1;

    public DonHangDAO() {
        // Thêm dữ liệu mẫu
        themDonHangMau();
    }

    private void themDonHangMau() {
        // Tạo khách hàng mẫu
        KhachHangDAO khDAO = new KhachHangDAO();
        KhachHang kh1 = khDAO.timKhachHangTheoMa(1);
        KhachHang kh2 = khDAO.timKhachHangTheoMa(2);

        // Tạo dịch vụ mẫu
        DichVuDAO dvDAO = new DichVuDAO();
        DichVu dv1 = dvDAO.timDichVuTheoMa(1);
        DichVu dv2 = dvDAO.timDichVuTheoMa(2);
        DichVu dv3 = dvDAO.timDichVuTheoMa(5);

        // Tạo chi tiết đơn hàng
        List<ChiTietDonHang> chiTiet1 = new ArrayList<>();
        chiTiet1.add(new ChiTietDonHang(dv1, 2));
        chiTiet1.add(new ChiTietDonHang(dv3, 3));

        List<ChiTietDonHang> chiTiet2 = new ArrayList<>();
        chiTiet2.add(new ChiTietDonHang(dv2, 1));
        chiTiet2.add(new ChiTietDonHang(dv1, 1));
        chiTiet2.add(new ChiTietDonHang(dv3, 2));

        // Tạo đơn hàng mẫu
        DonHang dh1 = new DonHang(nextMaDH++, kh1, new Date(),
                new Date(System.currentTimeMillis() + 86400000), chiTiet1, "Đang xử lý");

        DonHang dh2 = new DonHang(nextMaDH++, kh2,
                new Date(System.currentTimeMillis() - 86400000),
                new Date(), chiTiet2, "Hoàn thành");

        danhSachDonHang.add(dh1);
        danhSachDonHang.add(dh2);
    }

    public List<DonHang> layTatCaDonHang() {
        return danhSachDonHang;
    }

    public void themDonHang(DonHang donHang) {
        donHang.setMaDH(nextMaDH++);
        danhSachDonHang.add(donHang);
    }

    public void suaDonHang(DonHang donHang) {
        for (int i = 0; i < danhSachDonHang.size(); i++) {
            if (danhSachDonHang.get(i).getMaDH() == donHang.getMaDH()) {
                danhSachDonHang.set(i, donHang);
                break;
            }
        }
    }

    public void xoaDonHang(int maDH) {
        for (int i = 0; i < danhSachDonHang.size(); i++) {
            if (danhSachDonHang.get(i).getMaDH() == maDH) {
                danhSachDonHang.remove(i);
                break;
            }
        }
    }

    public DonHang timDonHangTheoMa(int maDH) {
        for (DonHang dh : danhSachDonHang) {
            if (dh.getMaDH() == maDH) {
                return dh;
            }
        }
        return null;
    }

    public List<DonHang> timDonHangTheoKhachHang(String tenKH) {
        List<DonHang> ketQua = new ArrayList<>();
        for (DonHang dh : danhSachDonHang) {
            if (dh.getKhachHang().getHoTen().toLowerCase().contains(tenKH.toLowerCase())) {
                ketQua.add(dh);
            }
        }
        return ketQua;
    }

    public List<DonHang> timDonHangTheoTrangThai(String trangThai) {
        List<DonHang> ketQua = new ArrayList<>();
        for (DonHang dh : danhSachDonHang) {
            if (dh.getTrangThai().equalsIgnoreCase(trangThai)) {
                ketQua.add(dh);
            }
        }
        return ketQua;
    }

    public double tinhDoanhThuTheoNgay(Date ngay) {
        double doanhThu = 0;
        for (DonHang dh : danhSachDonHang) {
            if (dh.getNgayNhan().equals(ngay) && "Hoàn thành".equals(dh.getTrangThai())) {
                doanhThu += dh.getTongTien();
            }
        }
        return doanhThu;
    }

    public double tinhTongDoanhThu() {
        double tong = 0;
        for (DonHang dh : danhSachDonHang) {
            if ("Hoàn thành".equals(dh.getTrangThai())) {
                tong += dh.getTongTien();
            }
        }
        return tong;
    }
}
