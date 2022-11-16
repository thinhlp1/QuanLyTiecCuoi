
package com.happywedding.model;


public class KhachHang {
    private int maKH;
    private String hoTen;
    private String soDienThoai;
    private String CCCD;
    private String diaChi;
    private String hoTenCoDau;
    private String hoTenChuRe;

    public KhachHang(int maKH, String hoTen, String soDienThoai, String CCCD, String diaChi, String hoTenCoDau, String hoTenChuRe) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.hoTenCoDau = hoTenCoDau;
        this.hoTenChuRe = hoTenChuRe;
    }

    public KhachHang() {
  
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHoTenCoDau() {
        return hoTenCoDau;
    }

    public void setHoTenCoDau(String hoTenCoDau) {
        this.hoTenCoDau = hoTenCoDau;
    }

    public String getHoTenChuRe() {
        return hoTenChuRe;
    }

    public void setHoTenChuRe(String hoTenChuRe) {
        this.hoTenChuRe = hoTenChuRe;
    }

   
}
