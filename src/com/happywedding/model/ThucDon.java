package com.happywedding.model;

public class ThucDon {

    private String maTD;
    private String tenTD;
    private long gia;
    private String ghiChu;

    public ThucDon(String maTD, String tenTD, long gia, String ghiChu) {
        this.maTD = maTD;
        this.tenTD = tenTD;
        this.gia = gia;
        this.ghiChu = ghiChu;
    }

    public ThucDon() {
   
    }

    public String getMaTD() {
        return maTD;
    }

    public void setMaTD(String maTD) {
        this.maTD = maTD;
    }

    public String getTenTD() {
        return tenTD;
    }

    public void setTenTD(String tenTD) {
        this.tenTD = tenTD;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
