
package com.happywedding.model;

public class VaiTro {
    private String maVT;
    private String maPB;
    private String tenVT;

    public VaiTro(String maVT, String maPB, String tenVT) {
        this.maVT = maVT;
        this.maPB = maPB;
        this.tenVT = tenVT;
    }

    public VaiTro() {
    }
    
    @Override
    public String toString() {
      return tenVT ;
    }
    

    public String getMaVT() {
        return maVT;
    }

    public void setMaVT(String maVT) {
        this.maVT = maVT;
    }

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public String getTenVT() {
        return tenVT;
    }

    public void setTenVT(String tenVT) {
        this.tenVT = tenVT;
    }

    
}
