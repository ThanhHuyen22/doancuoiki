/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.DonHang;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThongKeDAO {

    private DonHangDAO donHangDAO = new DonHangDAO();

    // Thống kê tổng quan
    public Map<String, Object> thongKeTongQuan() {
        Map<String, Object> result = new HashMap<>();

        List<DonHang> allOrders = donHangDAO.layTatCaDonHang();
        double tongDoanhThu = 0;
        int tongDonHang = allOrders.size();
        int donMoi = 0, donDangXuLy = 0, donHoanThanh = 0, donDaHuy = 0;

        for (DonHang dh : allOrders) {
            // Tính doanh thu từ đơn hoàn thành
            if ("Hoàn thành".equals(dh.getTrangThai())) {
                tongDoanhThu += dh.getTongTien();
            }

            // Đếm theo trạng thái
            switch (dh.getTrangThai()) {
                case "Mới":
                    donMoi++;
                    break;
                case "Đang xử lý":
                    donDangXuLy++;
                    break;
                case "Hoàn thành":
                    donHoanThanh++;
                    break;
                case "Đã hủy":
                    donDaHuy++;
                    break;
            }
        }

        result.put("tongDoanhThu", tongDoanhThu);
        result.put("tongDonHang", tongDonHang);
        result.put("donMoi", donMoi);
        result.put("donDangXuLy", donDangXuLy);
        result.put("donHoanThanh", donHoanThanh);
        result.put("donDaHuy", donDaHuy);
        result.put("tyLeHoanThanh", tongDonHang > 0 ? (donHoanThanh * 100.0 / tongDonHang) : 0);

        return result;
    }

    // Thống kê doanh thu theo ngày
    public double thongKeDoanhThuTheoNgay(Date ngay) {
        double doanhThu = 0;
        for (DonHang dh : donHangDAO.layTatCaDonHang()) {
            if (isSameDay(dh.getNgayNhan(), ngay) && "Hoàn thành".equals(dh.getTrangThai())) {
                doanhThu += dh.getTongTien();
            }
        }
        return doanhThu;
    }

    // Thống kê doanh thu theo tháng
    public double thongKeDoanhThuTheoThang(int thang, int nam) {
        double doanhThu = 0;
        for (DonHang dh : donHangDAO.layTatCaDonHang()) {
            if (isSameMonth(dh.getNgayNhan(), thang, nam) && "Hoàn thành".equals(dh.getTrangThai())) {
                doanhThu += dh.getTongTien();
            }
        }
        return doanhThu;
    }

    // Thống kê doanh thu theo năm
    public double thongKeDoanhThuTheoNam(int nam) {
        double doanhThu = 0;
        for (DonHang dh : donHangDAO.layTatCaDonHang()) {
            if (isSameYear(dh.getNgayNhan(), nam) && "Hoàn thành".equals(dh.getTrangThai())) {
                doanhThu += dh.getTongTien();
            }
        }
        return doanhThu;
    }

    // Thống kê dịch vụ bán chạy
    public Map<String, Integer> thongKeDichVuBanChay() {
        Map<String, Integer> result = new HashMap<>();
        DichVuDAO dichVuDAO = new DichVuDAO();

        for (DonHang dh : donHangDAO.layTatCaDonHang()) {
            if (dh.getChiTietDonHang() != null) {
                for (model.ChiTietDonHang ct : dh.getChiTietDonHang()) {
                    String tenDV = ct.getDichVu().getTenDV();
                    result.put(tenDV, result.getOrDefault(tenDV, 0) + ct.getSoLuong());
                }
            }
        }

        return result;
    }

    // Thống kê khách hàng thân thiết
    public Map<String, Integer> thongKeKhachHangThanThiet() {
        Map<String, Integer> result = new HashMap<>();

        for (DonHang dh : donHangDAO.layTatCaDonHang()) {
            String tenKH = dh.getKhachHang().getHoTen();
            result.put(tenKH, result.getOrDefault(tenKH, 0) + 1);
        }

        return result;
    }

    // Lấy danh sách đơn hàng theo khoảng thời gian
    public List<DonHang> layDonHangTheoKhoangThoiGian(Date fromDate, Date toDate) {
        List<DonHang> result = new ArrayList<>();

        for (DonHang dh : donHangDAO.layTatCaDonHang()) {
            if (!dh.getNgayNhan().before(fromDate) && !dh.getNgayNhan().after(toDate)) {
                result.add(dh);
            }
        }

        return result;
    }

    // Helper methods để so sánh ngày
    private boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        java.util.Calendar cal1 = java.util.Calendar.getInstance();
        java.util.Calendar cal2 = java.util.Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR)
                && cal1.get(java.util.Calendar.MONTH) == cal2.get(java.util.Calendar.MONTH)
                && cal1.get(java.util.Calendar.DAY_OF_MONTH) == cal2.get(java.util.Calendar.DAY_OF_MONTH);
    }

    private boolean isSameMonth(Date date, int thang, int nam) {
        if (date == null) {
            return false;
        }
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        return cal.get(java.util.Calendar.YEAR) == nam
                && cal.get(java.util.Calendar.MONTH) + 1 == thang;
    }

    private boolean isSameYear(Date date, int nam) {
        if (date == null) {
            return false;
        }
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        return cal.get(java.util.Calendar.YEAR) == nam;
    }

    // Thống kê doanh thu theo tuần
    public double thongKeDoanhThuTuanHienTai() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        Date startOfWeek = cal.getTime();

        cal.add(java.util.Calendar.DAY_OF_WEEK, 6);
        Date endOfWeek = cal.getTime();

        return tinhDoanhThuTheoKhoangThoiGian(startOfWeek, endOfWeek);
    }

    // Thống kê doanh thu theo tháng hiện tại
    public double thongKeDoanhThuThangHienTai() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
        Date startOfMonth = cal.getTime();

        cal.set(java.util.Calendar.DAY_OF_MONTH, cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH));
        Date endOfMonth = cal.getTime();

        return tinhDoanhThuTheoKhoangThoiGian(startOfMonth, endOfMonth);
    }

    // Tính doanh thu theo khoảng thời gian
    private double tinhDoanhThuTheoKhoangThoiGian(Date fromDate, Date toDate) {
        double doanhThu = 0;
        for (DonHang dh : donHangDAO.layTatCaDonHang()) {
            if (dh.getNgayNhan().after(fromDate)
                    && dh.getNgayNhan().before(toDate)
                    && "Hoàn thành".equals(dh.getTrangThai())) {
                doanhThu += dh.getTongTien();
            }
        }
        return doanhThu;
    }
}
