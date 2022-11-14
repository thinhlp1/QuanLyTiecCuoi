
package com.happywedding.model;

public class GoiDichVu {
    private String maGoi;
    private String maDV;
    private String tenDV;
    private String tenGoi;
    private long chiPhi;
    private String ghiChu;
    private String hinhAnh;

    public GoiDichVu(String maGoi, String maDV, String tenDV, String tenGoi, long chiPhi, String ghiChu, String hinhAnh) {
        this.maGoi = maGoi;
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.tenGoi = tenGoi;
        this.chiPhi = chiPhi;
        this.ghiChu = ghiChu;
        this.hinhAnh = hinhAnh;
    }

    
    
    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
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
