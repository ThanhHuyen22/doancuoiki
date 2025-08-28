/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class ChiTietDonHang {

    private DichVu dichVu;
    private int soLuong;
    private double thanhTien;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(DichVu dichVu, int soLuong) {
        this.dichVu = dichVu;
        this.soLuong = soLuong;
        this.thanhTien = dichVu.getGiaTien() * soLuong;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        this.thanhTien = dichVu.getGiaTien() * soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }
}
