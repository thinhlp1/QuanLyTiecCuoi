
package com.happywedding.model;


public class DanhMuc {
     private String maDM;
      private String tenDM;

    public DanhMuc(String maDM, String tenDM) {
        this.maDM = maDM;
        this.tenDM = tenDM;
    }

    public DanhMuc() {
    }
    
    @Override
    public String toString() {
        return    tenDM;
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
