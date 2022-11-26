package com.happywedding.model;

public class ThucDon {

    private String maTD;
    private String tenTD;
    private String ghiChu;

    public ThucDon(String maTD, String tenTD, String ghiChu) {
        this.maTD = maTD;
        this.tenTD = tenTD;

        this.ghiChu = ghiChu;
    }

    public ThucDon() {

    }

    @Override
    public String toString() {
        return tenTD;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
