
package com.model;


public class PhanCong {
    private int maPC;
    private String maHD;
    private String maNguoiPC;

    public PhanCong(int maPC, String maHD, String maNguoiPC) {
        this.maPC = maPC;
        this.maHD = maHD;
        this.maNguoiPC = maNguoiPC;
    }

    public int getMaPC() {
        return maPC;
    }

    public void setMaPC(int maPC) {
        this.maPC = maPC;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNguoiPC() {
        return maNguoiPC;
    }

    public void setMaNguoiPC(String maNguoiPC) {
        this.maNguoiPC = maNguoiPC;
    }

  
    
}
