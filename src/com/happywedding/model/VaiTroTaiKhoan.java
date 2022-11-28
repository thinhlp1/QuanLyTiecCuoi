
package com.happywedding.model;


public class VaiTroTaiKhoan {
     private String maVT;
     private String tenVT;

    public VaiTroTaiKhoan(String maVT, String tenVT) {
        this.maVT = maVT;
        this.tenVT = tenVT;
    }

    public VaiTroTaiKhoan() {
    }
    
    

    public String getMaVT() {
        return maVT;
    }

    public void setMaVT(String maVT) {
        this.maVT = maVT;
    }

    public String getTenVT() {
        return tenVT;
    }

    public void setTenVT(String tenVT) {
        this.tenVT = tenVT;
    }

   
     
}
