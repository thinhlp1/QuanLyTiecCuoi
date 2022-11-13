
package com.happywedding.model;


public class MonAn {
    private String maMA;
    private String tenMA;
    private String hinhAnh;
    private long giaTien;
    private String maPL;
    private String tenPL;

    public MonAn(String maMA, String tenMA, String hinhAnh, long giaTien, String maPL, String tenPL) {
        this.maMA = maMA;
        this.tenMA = tenMA;
        this.hinhAnh = hinhAnh;
        this.giaTien = giaTien;
        this.maPL = maPL;
        this.tenPL = tenPL;
    }

  
    
    public String getTenPL() {
        return tenPL;
    }

    public void setTenPL(String tenPL) {
        this.tenPL = tenPL;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(long giaTien) {
        this.giaTien = giaTien;
    }

    public String getMaPL() {
        return maPL;
    }

    public void setMaPL(String maPL) {
        this.maPL = maPL;
    }

  
}
