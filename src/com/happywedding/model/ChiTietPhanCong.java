/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ChiTietPhanCong {


    private int maPC;
    private String maNV;
    private String tenNV;
    private String maVT;
    private String tenVT;
    private Date ngayPhanCong;
    private String thoiGianBatDau;
    private String thoiGianKetThuc;
    private String maXe;
    private String tenXe;

    public ChiTietPhanCong() {
    }

    public ChiTietPhanCong(int maPC, String maNV, String tenNV, String maVT, Date ngayPhanCong, String thoiGianBatDau, String thoiGianKetThuc, String maXe, String tenXe) {
        this.maPC = maPC;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.maVT = maVT;
        this.ngayPhanCong = ngayPhanCong;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.maXe = maXe;
        this.tenXe = tenXe;
    }

    public String getTenVT() {
        return tenVT;
    }

    public void setTenVT(String tenVT) {
        this.tenVT = tenVT;
    }
    
    

    public int getMaPC() {
        return maPC;
    }

    public void setMaPC(int maPC) {
        this.maPC = maPC;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getMaVT() {
        return maVT;
    }

    public void setMaVT(String maVT) {
        this.maVT = maVT;
    }

    public Date getNgayPhanCong() {
        return ngayPhanCong;
    }

    public void setNgayPhanCong(Date ngayPhanCong) {
        this.ngayPhanCong = ngayPhanCong;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    
}
