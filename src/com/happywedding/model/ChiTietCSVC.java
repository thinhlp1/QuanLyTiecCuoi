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
public class ChiTietCSVC {
    private String maHD;
    private String maCSVC;
    private String tenCSVC;
    private String maDV;
    private String tenDV;
    private long ChiPhiPhatSinh;
    private long ghiChu;

    public ChiTietCSVC(String maHD, String maCSVC, String tenCSVC, String maDV, String tenDV, long ChiPhiPhatSinh, long ghiChu) {
        this.maHD = maHD;
        this.maCSVC = maCSVC;
        this.tenCSVC = tenCSVC;
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.ChiPhiPhatSinh = ChiPhiPhatSinh;
        this.ghiChu = ghiChu;
    }

    
    
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaCSVC() {
        return maCSVC;
    }

    public void setMaCSVC(String maCSVC) {
        this.maCSVC = maCSVC;
    }

    public String getTenCSVC() {
        return tenCSVC;
    }

    public void setTenCSVC(String tenCSVC) {
        this.tenCSVC = tenCSVC;
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

    public long getChiPhiPhatSinh() {
        return ChiPhiPhatSinh;
    }

    public void setChiPhiPhatSinh(long ChiPhiPhatSinh) {
        this.ChiPhiPhatSinh = ChiPhiPhatSinh;
    }

    public long getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(long ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
