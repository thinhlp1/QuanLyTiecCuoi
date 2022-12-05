
package com.happywedding.model;


public class DanhMucCon {
    private String maDMC;
    
      private String maDM;
      private String tenDM;

    public DanhMucCon(String maDMC, String maDM, String tenDM) {
        this.maDMC = maDMC;
        this.maDM = maDM;
        this.tenDM = tenDM;
    }

    public DanhMucCon() {
    }
    
    @Override
    public String toString() {
        return maDMC  ;
    }

    public String getMaDMC() {
        return maDMC;
    }

    public void setMaDMC(String maDMC) {
        this.maDMC = maDMC;
    }

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

      
}
