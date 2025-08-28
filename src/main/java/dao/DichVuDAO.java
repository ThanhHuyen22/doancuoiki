/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.DichVu;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {

    // Danh sách dịch vụ (thay thế database)
    private List<DichVu> danhSachDichVu = new ArrayList<>();
    private int nextMaDV = 1; // Biến tự tăng mã DV

    // Constructor - thêm dữ liệu mẫu
    public DichVuDAO() {
        // Thêm dữ liệu mẫu để test
        themDichVu(new DichVu(0, "Giặt khô", 20000, "cái"));
        themDichVu(new DichVu(0, "Giặt ướt", 15000, "cái"));
        themDichVu(new DichVu(0, "Giặt hấp", 25000, "cái"));
        themDichVu(new DichVu(0, "Giặt theo kg", 40000, "kg"));
        themDichVu(new DichVu(0, "Ủi đồ", 10000, "cái"));
        themDichVu(new DichVu(0, "Giặt nệm", 80000, "cái"));
    }

    // Lấy tất cả dịch vụ
    public List<DichVu> layTatCaDichVu() {
        return danhSachDichVu;
    }

    // Thêm dịch vụ mới
    public void themDichVu(DichVu dichVu) {
        dichVu.setMaDV(nextMaDV++); // Tự động gán mã mới
        danhSachDichVu.add(dichVu);
    }

    // Sửa thông tin dịch vụ
    public void suaDichVu(DichVu dichVu) {
        for (int i = 0; i < danhSachDichVu.size(); i++) {
            if (danhSachDichVu.get(i).getMaDV() == dichVu.getMaDV()) {
                danhSachDichVu.set(i, dichVu);
                break;
            }
        }
    }

    // Xóa dịch vụ theo mã
    public void xoaDichVu(int maDV) {
        for (int i = 0; i < danhSachDichVu.size(); i++) {
            if (danhSachDichVu.get(i).getMaDV() == maDV) {
                danhSachDichVu.remove(i);
                break;
            }
        }
    }

    // Tìm dịch vụ theo mã
    public DichVu timDichVuTheoMa(int maDV) {
        for (DichVu dv : danhSachDichVu) {
            if (dv.getMaDV() == maDV) {
                return dv;
            }
        }
        return null;
    }

    // Tìm dịch vụ theo tên
    public List<DichVu> timDichVuTheoTen(String ten) {
        List<DichVu> ketQua = new ArrayList<>();
        for (DichVu dv : danhSachDichVu) {
            if (dv.getTenDV().toLowerCase().contains(ten.toLowerCase())) {
                ketQua.add(dv);
            }
        }
        return ketQua;
    }

    // Lấy dịch vụ theo khoảng giá
    public List<DichVu> timDichVuTheoGia(double giaMin, double giaMax) {
        List<DichVu> ketQua = new ArrayList<>();
        for (DichVu dv : danhSachDichVu) {
            if (dv.getGiaTien() >= giaMin && dv.getGiaTien() <= giaMax) {
                ketQua.add(dv);
            }
        }
        return ketQua;
    }
}
