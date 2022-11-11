
package com.model;


public class DichVu {
    private String maDV;
    private String maPLDV;
    private String tenDV;

    public DichVu(String maDV, String maPLDV, String tenDV) {
        this.maDV = maDV;
        this.maPLDV = maPLDV;
        this.tenDV = tenDV;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getMaPLDV() {
        return maPLDV;
    }

    public void setMaPLDV(String maPLDV) {
        this.maPLDV = maPLDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    
}
