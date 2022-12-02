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
public class HopDongDichVuDiKem {
    
   private String maHD;
   private long chiPhi;
   private long chiPhiPhatSinh;
   private String ghiChu;

    public HopDongDichVuDiKem(String maHD, long chiPhi, long chiPhiPhatSinh, String ghiChu) {
        this.maHD = maHD;
        this.chiPhi = chiPhi;
        this.chiPhiPhatSinh = chiPhiPhatSinh;
        this.ghiChu = ghiChu;
    }

    public HopDongDichVuDiKem() {
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
   
   
    
    
}
