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
    private long duaDon;
    private long diKem;
    private long thucDon;
    private long TongTien;
    private String maTT;
    private String tenTT;
    private long tongTien;

    public HoaDon(int maHoaDon, String MaHD, Date ngayLap, String maNV, String tenNV, long trangTriCong, long trangTriSanKhau, long trangTriBanTiec, long ngheThuat, long duaDon, long diKem, long thucDon, long TongTien, String maTT, String tenTT, long tongTien) {
        this.maHoaDon = maHoaDon;
        this.MaHD = MaHD;
        this.ngayLap = ngayLap;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.trangTriCong = trangTriCong;
        this.trangTriSanKhau = trangTriSanKhau;
        this.trangTriBanTiec = trangTriBanTiec;
        this.ngheThuat = ngheThuat;
        this.duaDon = duaDon;
        this.diKem = diKem;
        this.thucDon = thucDon;
        this.TongTien = TongTien;
        this.maTT = maTT;
        this.tenTT = tenTT;
        this.tongTien = tongTien;
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

    public long getDuaDon() {
        return duaDon;
    }

    public void setDuaDon(long duaDon) {
        this.duaDon = duaDon;
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

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
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

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

}
