package dao;

import model.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class KhachHangDAO {

    private List<KhachHang> danhSachKhachHang = new ArrayList<>();
    private int nextMaKH = 1;
    private final String TEN_FILE = "khachhang.txt";

    // Constructor
    public KhachHangDAO() {
        docFile(TEN_FILE);

        if (danhSachKhachHang.isEmpty()) {
            themKhachHang(new KhachHang(0, "Nguyễn Văn Đạt", "0912345678", "123 đường ABC, Quận 1"));
            themKhachHang(new KhachHang(0, "Trần Thị Bình", "0987654321", "456 đường XYZ, Quận 2"));
            themKhachHang(new KhachHang(0, "Lê Văn Cường", "0905123456", "789 đường DEF, Quận 3"));
            themKhachHang(new KhachHang(0, "Đinh Thiên Bảo", "0905123456", "789 đường Gò Vấp, Quận 12"));
            themKhachHang(new KhachHang(0, "Phạm Thanh Huyền", "0985405359", "789 đường Hà Nội, Quận 13"));
            luuKhachHang();
        }
    }

    public List<KhachHang> layTatCaKhachHang() {
        return danhSachKhachHang;
    }

    public void themKhachHang(KhachHang khachHang) {
        khachHang.setMaKH(nextMaKH++);
        danhSachKhachHang.add(khachHang);
        luuKhachHang();
    }

    public void suaKhachHang(KhachHang khachHang) {
        for (int i = 0; i < danhSachKhachHang.size(); i++) {
            if (danhSachKhachHang.get(i).getMaKH() == khachHang.getMaKH()) {
                danhSachKhachHang.set(i, khachHang);
                luuKhachHang();
                break;
            }
        }
    }

    public void xoaKhachHang(int maKH) {
        for (int i = 0; i < danhSachKhachHang.size(); i++) {
            if (danhSachKhachHang.get(i).getMaKH() == maKH) {
                danhSachKhachHang.remove(i);
                luuKhachHang();
                break;
            }
        }
    }

    public void luuKhachHang() {
        ghiFile(TEN_FILE);
    }

    public KhachHang timKhachHangTheoMa(int maKH) {
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getMaKH() == maKH) {
                return kh;
            }
        }
        return null;
    }

    public List<KhachHang> timKhachHangTheoTen(String ten) {
        List<KhachHang> ketQua = new ArrayList<>();
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getHoTen().toLowerCase().contains(ten.toLowerCase())) {
                ketQua.add(kh);
            }
        }
        return ketQua;
    }

    public List<KhachHang> timKhachHangTheoSDT(String sdt) {
        List<KhachHang> ketQua = new ArrayList<>();
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getSoDienThoai().contains(sdt)) {
                ketQua.add(kh);
            }
        }
        return ketQua;
    }

    // ✅ Ghi file UTF-8
    public void ghiFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(tenFile), StandardCharsets.UTF_8))) {
            for (KhachHang kh : danhSachKhachHang) {
                String dong = kh.getMaKH() + ";"
                        + kh.getHoTen() + ";"
                        + kh.getSoDienThoai() + ";"
                        + kh.getDiaChi();
                bw.write(dong);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void docFile(String filePath) {
        danhSachKhachHang.clear();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    int maKH = Integer.parseInt(parts[0]);
                    String hoTen = parts[1];
                    String sdt = parts[2];
                    String diaChi = parts[3];
                    danhSachKhachHang.add(new KhachHang(maKH, hoTen, sdt, diaChi));
                    nextMaKH = Math.max(nextMaKH, maKH + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inDanhSach() {
        for (KhachHang kh : danhSachKhachHang) {
            System.out.println(kh.getMaKH() + " - " + kh.getHoTen() + " - "
                    + kh.getSoDienThoai() + " - " + kh.getDiaChi());
        }
    }
}
