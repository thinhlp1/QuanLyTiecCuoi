package com.happywedding.model;





public class CoSoVatChat {
    private String maCSVC;
    private String tenCSVC;
    private String maDanhMuc;
    private String maDanhMucCon;
    private String tenDanhMuc;
    private long soLuong;
    private long giaThue;
    private String ghiChu;

    public CoSoVatChat(String maCSVC, String tenCSVC, String maDanhMuc, String tenDanhMuc, long soLuong, long giaThue, String ghiChu) {
        this.maCSVC = maCSVC;
        this.tenCSVC = tenCSVC;
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.soLuong = soLuong;
        this.giaThue = giaThue;
        this.ghiChu = ghiChu;
    }

    public CoSoVatChat() {
    }
    
    @Override
    public String toString() {
        return maCSVC+   tenCSVC;
    }

    public String getMaDanhMucCon() {
        return maDanhMucCon;
    }

    public void setMaDanhMucCon(String maDanhMucCon) {
        this.maDanhMucCon = maDanhMucCon;
    }

//    @Override
//    public String toString() {
//       return tenCSVC;
//    }
    
    

    
        
    public long getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(long giaThue) {
        this.giaThue = giaThue;
    }

  
    

    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
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
