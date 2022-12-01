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
public class ChiTietDatMon {
    private String maHD;
    private String maMA;
    private String tenMA;
    private String maPL;
    private String maTD;
    private long gia;
    private int soLuong;
    private long chiPhiPhatSinh;
    private int thuTu;
    private String ghiChu;

    public ChiTietDatMon(String maHD, String maMA, String tenMA, long gia, long chiPhiPhatSinh, int thuTu, String ghiChu) {
        this.maHD = maHD;
        this.maMA = maMA;
        this.tenMA = tenMA;
        this.gia = gia;
        this.chiPhiPhatSinh = chiPhiPhatSinh;
        this.thuTu = thuTu;
        this.ghiChu = ghiChu;
    }

    public ChiTietDatMon() {
    }

    public String getMaPL() {
        return maPL;
    }

    public void setMaPL(String maPL) {
        this.maPL = maPL;
    }
    
    
    
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaTD() {
        return maTD;
    }

    public void setMaTD(String maTD) {
        this.maTD = maTD;
    }
    
    
    
    
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaMA() {
        return maMA;
    }

    public void setMaMA(String maMA) {
        this.maMA = maMA;
    }

    public String getTenMA() {
        return tenMA;
    }

    public void setTenMA(String tenMA) {
        this.tenMA = tenMA;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public long getChiPhiPhatSinh() {
        return chiPhiPhatSinh;
    }

    public void setChiPhiPhatSinh(long chiPhiPhatSinh) {
        this.chiPhiPhatSinh = chiPhiPhatSinh;
    }

    public int getThuTu() {
        return thuTu;
    }

    public void setThuTu(int thuTu) {
        this.thuTu = thuTu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
  
    
    
}
