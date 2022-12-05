package com.happywedding.model;

public class PhanLoaiSanh{
    private String maPL;
    private String tenPL;

    public PhanLoaiSanh(String maPL, String tenPL) {
        this.maPL = maPL;
        this.tenPL = tenPL;
    }

    public PhanLoaiSanh() {
    }
     @Override
    public String toString() {
      return tenPL ;
    }
    
    
    public String getMaPL() {
        return maPL;
    }

    public void setMaPL(String maPL) {
        this.maPL = maPL;
    }

    public String getTenPL() {
        return tenPL;
    }

    public void setTenPL(String tenPL) {
        this.tenPL = tenPL;
    }

    
}