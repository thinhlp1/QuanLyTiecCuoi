package com.happywedding.model;

import java.util.Date;

public class HoaDon { // hoa don

    private int maHoaDon;
    private String MaHD;
    private Date ngayLap;
    private String maNV;
    private String tenNV;
    private long trangTriCong;
    private long trangTriSanKhau;
    private long trangTriBanTiec;
    private long ngheThuat;
    private long diKem;
    private long thucDon;
    private long tienCoc;
   private long phatSinh;
    private int trangTha;
    private long tongTien;

    
    
     public HoaDon(int maHoaDon, String MaHD, Date ngayLap, String maNV, String tenNV, long trangTriCong, long trangTriSanKhau, long trangTriBanTiec, long ngheThuat, long diKem, long thucDon, long tienCoc, long phatSinh, int trangTha, long tongTien) {
        this.maHoaDon = maHoaDon;
        this.MaHD = MaHD;
        this.ngayLap = ngayLap;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.trangTriCong = trangTriCong;
        this.trangTriSanKhau = trangTriSanKhau;
        this.trangTriBanTiec = trangTriBanTiec;
        this.ngheThuat = ngheThuat;
        this.diKem = diKem;
        this.thucDon = thucDon;
        this.tienCoc = tienCoc;
        this.phatSinh = phatSinh;
        this.trangTha = trangTha;
        this.tongTien = tongTien;
    }
    
    public long getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(long tienCoc) {
        this.tienCoc = tienCoc;
    }

    public long getPhatSinh() {
        return phatSinh;
    }

    public void setPhatSinh(long phatSinh) {
        this.phatSinh = phatSinh;
    }

    public int getTrangTha() {
        return trangTha;
    }

    public void setTrangTha(int trangTha) {
        this.trangTha = trangTha;
    }

   


    
    
    
    public long getTrangTriCong() {
        return trangTriCong;
    }

    public void setTrangTriCong(long trangTriCong) {
        this.trangTriCong = trangTriCong;
    }

    public long getTrangTriSanKhau() {
        return trangTriSanKhau;
    }

    public void setTrangTriSanKhau(long trangTriSanKhau) {
        this.trangTriSanKhau = trangTriSanKhau;
    }

    public long getTrangTriBanTiec() {
        return trangTriBanTiec;
    }

    public void setTrangTriBanTiec(long trangTriBanTiec) {
        this.trangTriBanTiec = trangTriBanTiec;
    }

    public long getNgheThuat() {
        return ngheThuat;
    }

    public void setNgheThuat(long ngheThuat) {
        this.ngheThuat = ngheThuat;
    }

 

    public long getDiKem() {
        return diKem;
    }

    public void setDiKem(long diKem) {
        this.diKem = diKem;
    }

    public long getThucDon() {
        return thucDon;
    }

    public void setThucDon(long thucDon) {
        this.thucDon = thucDon;
    }

    

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

 
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }


    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

}
