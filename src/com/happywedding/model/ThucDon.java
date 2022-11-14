package com.happywedding.model;

public class ThucDon {

    private String maTD;
    private String tenTD;
    private long gia;
    private int ghiChu;

    public ThucDon(String maTD, String tenTD, long gia, int ghiChu) {
        this.maTD = maTD;
        this.tenTD = tenTD;
        this.gia = gia;
        this.ghiChu = ghiChu;
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

    public int getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(int ghiChu) {
        this.ghiChu = ghiChu;
    }

}
