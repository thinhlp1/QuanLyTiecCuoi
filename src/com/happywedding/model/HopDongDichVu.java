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
public class HopDongDichVu {
    private String maHD;
    private String maDV;
    private String maGoi;
    
    private String ghiChu;
    private long chiPhiPhatSinh;
    private long chiPhi;

    public HopDongDichVu(String maHD, String maDV, String maGoi, String ghiChu, long chiPhiPhatSinh, long chiPhi) {
        this.maHD = maHD;
        this.maDV = maDV;
        this.maGoi = maGoi;
        this.ghiChu = ghiChu;
        this.chiPhiPhatSinh = chiPhiPhatSinh;
        this.chiPhi = chiPhi;
    }
    public HopDongDichVu(){
        
    }
    
    
    
    public String getMaGoi() {
        return maGoi;
    }

    public void setMaGoi(String maGoi) {
        this.maGoi = maGoi;
    }

    public long getChiPhiPhatSinh() {
        return chiPhiPhatSinh;
    }

    public void setChiPhiPhatSinh(long chiPhiPhatSinh) {
        this.chiPhiPhatSinh = chiPhiPhatSinh;
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
