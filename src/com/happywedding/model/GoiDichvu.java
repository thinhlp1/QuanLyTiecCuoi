
package com.happywedding.model;

public class GoiDichvu {
    private String maGoi;
    private String maDV;
    private String tenGoi;
    private long chiPhi;
    private String ghiChu;
    private String hinhAnh;
    
    

    public GoiDichvu(String maGoi, String maDV, String tenGoi, long chiPhi, String ghiChu, String hinhAnh) {
        this.maGoi = maGoi;
        this.maDV = maDV;
        this.tenGoi = tenGoi;
        this.chiPhi = chiPhi;
        this.ghiChu = ghiChu;
        this.hinhAnh = hinhAnh;
    }

    public String getMaGoi() {
        return maGoi;
    }

    public void setMaGoi(String maGoi) {
        this.maGoi = maGoi;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenGoi() {
        return tenGoi;
    }

    public void setTenGoi(String tenGoi) {
        this.tenGoi = tenGoi;
    }

    public long getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(long chiPhi) {
        this.chiPhi = chiPhi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

}
