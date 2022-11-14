/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.model;

/**
 *
 * @author ADMIN
 */
public class ChiTietDichVu {
    private String maHD;
    private String maDV;
    private String ghiChu;
    private long chiPhi;

    public ChiTietDichVu(String maHD, String maDV, String ghiChu, long chiPhi) {
        this.maHD = maHD;
        this.maDV = maDV;
        this.ghiChu = ghiChu;
        this.chiPhi = chiPhi;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public long getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(long chiPhi) {
        this.chiPhi = chiPhi;
    }
    
    
}
