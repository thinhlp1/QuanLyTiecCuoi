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
public class ChiTietDichVuDiKem {
    private String maHD;
    private String maDV;
    private String tenDV;
    private String ghiChu;
    private long chiPhi;
    private long chiPhiPhatSinh;

    public ChiTietDichVuDiKem(String maHD, String maDV, String tenDV, String ghiChu, long chiPhi, long chiPhiPhatSinh) {
        this.maHD = maHD;
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.ghiChu = ghiChu;
        this.chiPhi = chiPhi;
        this.chiPhiPhatSinh = chiPhiPhatSinh;
    }

    public ChiTietDichVuDiKem() {
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

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
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

    public long getChiPhiPhatSinh() {
        return chiPhiPhatSinh;
    }

    public void setChiPhiPhatSinh(long chiPhiPhatSinh) {
        this.chiPhiPhatSinh = chiPhiPhatSinh;
    }
    
    
    
}
