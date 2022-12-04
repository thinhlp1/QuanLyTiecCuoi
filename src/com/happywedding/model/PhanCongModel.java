package com.happywedding.model;

public class PhanCongModel {

    private int maPC;
    private String maHD;
    private String maNguoiPC;

    public PhanCongModel(int maPC, String maHD, String maNguoiPC) {
        this.maPC = maPC;
        this.maHD = maHD;
        this.maNguoiPC = maNguoiPC;
    }

    public PhanCongModel() {
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
