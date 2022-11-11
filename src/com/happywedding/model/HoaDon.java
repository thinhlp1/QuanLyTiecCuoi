
package com.happywedding.model;
import java.util.Date;


public class HoaDon { // hoa don
     private int maHoaDon;
      private String MaHD;
       private Date ngayLap;
        private String maNV;
         private String maTT;
          private long tongTien;

    public HoaDon(int maHoaDon, String MaHD, Date ngayLap, String maNV, String maTT, long tongTien) {
        this.maHoaDon = maHoaDon;
        this.MaHD = MaHD;
        this.ngayLap = ngayLap;
        this.maNV = maNV;
        this.maTT = maTT;
        this.tongTien = tongTien;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

 
          
}
