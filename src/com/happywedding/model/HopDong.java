package com.happywedding.model;

import java.util.Date;

public class HopDong {

    private String maHD;
    private String maNL;
    private Date ngayLap;
    private Date ngayDuyet;
    private String maND;
    private String sanh;
    private long soLuongBan;
    private int maKH;
    private Date ngayToChuc;
    private String thoiGianBatDau;
    private String thoiGianKetThuc;
    private String TrangThai;
    private long Thue;
    private long TienCoc;
    private long chiPhi;
    private long ChiPhiPhatSinh;
    private long TongTien;

    public HopDong(String maHD, String maNL, Date ngayLap, Date ngayDuyet, String maND, String sanh, long soLuongBan, int maKH, Date ngayToChuc, String thoiGianBatDau, String thoiGianKetThuc, String TrangThai, long Thue, long TienCoc, long chiPhi, long ChiPhiPhatSinh, long TongTien) {
        this.maHD = maHD;
        this.maNL = maNL;
        this.ngayLap = ngayLap;
        this.ngayDuyet = ngayDuyet;
        this.maND = maND;
        this.sanh = sanh;
        this.soLuongBan = soLuongBan;
        this.maKH = maKH;
        this.ngayToChuc = ngayToChuc;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.TrangThai = TrangThai;
        this.Thue = Thue;
        this.TienCoc = TienCoc;
        this.chiPhi = chiPhi;
        this.ChiPhiPhatSinh = ChiPhiPhatSinh;
        this.TongTien = TongTien;
    }
    
    public HopDong(){
        
    }
    
    
    
    public long getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(long chiPhi) {
        this.chiPhi = chiPhi;
    }

    

    public HopDong() {
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNL() {
        return maNL;
    }

    public void setMaNL(String maNL) {
        this.maNL = maNL;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Date getNgayDuyet() {
        return ngayDuyet;
    }

    public void setNgayDuyet(Date ngayDuyet) {
        this.ngayDuyet = ngayDuyet;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getSanh() {
        return sanh;
    }

    public void setSanh(String sanh) {
        this.sanh = sanh;
    }

    public long getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(long soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public Date getNgayToChuc() {
        return ngayToChuc;
    }

    public void setNgayToChuc(Date ngayToChuc) {
        this.ngayToChuc = ngayToChuc;
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

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public long getThue() {
        return Thue;
    }

    public void setThue(long Thue) {
        this.Thue = Thue;
    }

    public long getTienCoc() {
        return TienCoc;
    }

    public void setTienCoc(long TienCoc) {
        this.TienCoc = TienCoc;
    }

    public long getChiPhiPhatSinh() {
        return ChiPhiPhatSinh;
    }

    public void setChiPhiPhatSinh(long ChiPhiPhatSinh) {
        this.ChiPhiPhatSinh = ChiPhiPhatSinh;
    }

    public long getTongTien() {
        return TongTien;
    }

    public void setTongTien(long TongTien) {
        this.TongTien = TongTien;
    }

}
