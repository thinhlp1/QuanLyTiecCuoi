package com.happywedding.model;





public class CoSoVatChat {
    private String maCSVC;
    private String tenCSVC;
    private String danhMuc;
    private long soLuong;
    private String ghiChu;

    public CoSoVatChat(String maCSVC, String tenCSVC, String danhMuc, long soLuong, String ghiChu) {
        this.maCSVC = maCSVC;
        this.tenCSVC = tenCSVC;
        this.danhMuc = danhMuc;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    public String getMaCSVC() {
        return maCSVC;
    }

    public void setMaCSVC(String maCSVC) {
        this.maCSVC = maCSVC;
    }

    public String getTenCSVC() {
        return tenCSVC;
    }

    public void setTenCSVC(String tenCSVC) {
        this.tenCSVC = tenCSVC;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public long getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(long soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
    
}
