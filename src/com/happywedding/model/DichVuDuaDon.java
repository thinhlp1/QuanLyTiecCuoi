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
public class DichVuDuaDon {
    private String maHD;
    private Date ngayKhoiHanh;
    private String diaDiem;
    private String gioDi;
    private String gioVe;
    private String soLuong;
    private String ghiChu;

    public DichVuDuaDon(String maHD, Date ngayKhoiHanh, String diaDiem, String gioDi, String gioVe, String soLuong, String ghiChu) {
        this.maHD = maHD;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.diaDiem = diaDiem;
        this.gioDi = gioDi;
        this.gioVe = gioVe;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public void setNgayKhoiHanh(Date ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getGioDi() {
        return gioDi;
    }

    public void setGioDi(String gioDi) {
        this.gioDi = gioDi;
    }

    public String getGioVe() {
        return gioVe;
    }

    public void setGioVe(String gioVe) {
        this.gioVe = gioVe;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
