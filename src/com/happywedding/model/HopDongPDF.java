/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HopDongPDF {

  
    
    private List<HopDong> listHopDong ;

    // thông tin khách hàng
    private List<KhachHang> listKhachHang;
    
    

    // thông tin dịch vụ đăt món 
    private List<DichVuDatMon> listDichVuDatMon;

    // list chi tiết dịch vụ đặt 
    private List<ChiTietDatMon> listChiTietDatMon1;
    private List<ChiTietDatMon> listChiTietDatMon2;

    // thông tin trang trí bàn tiệc
    private List<HopDongDichVu> listTTBanTiec;

    private List<ChiTietDichVu> listChiTietBanTiec;

    // thông tin trang trí sân khấu
    private List<HopDongDichVu> listTTSanKhau;
    private List<ChiTietDichVu> listChiTietSanKhau;

    // thông tin trang trí cổng
   private List<HopDongDichVu> listTTCong;
    private List<ChiTietDichVu> listChiTietCong;

    // thông tin nghệ thuật
   private List<HopDongDichVu> listNgheThuat;
    private List<ChiTietDichVu> listChiTietNgheThuat;

    //thông tin dịch vụ đi kèm
    private List<DichVuDiKemModel> listDichVuDiKem;
    List<ChiTietDichVuDiKem> listChiTietDichVuDiKem ;

    public List<HopDong> getListHopDong() {
        return listHopDong;
    }

    public void setListHopDong(List<HopDong> listHopDong) {
        this.listHopDong = listHopDong;
    }

    public List<KhachHang> getListKhachHang() {
        return listKhachHang;
    }

    public void setListKhachHang(List<KhachHang> listKhachHang) {
        this.listKhachHang = listKhachHang;
    }

    public List<DichVuDatMon> getListDichVuDatMon() {
        return listDichVuDatMon;
    }

    public void setListDichVuDatMon(List<DichVuDatMon> listDichVuDatMon) {
        this.listDichVuDatMon = listDichVuDatMon;
    }

    public List<ChiTietDatMon> getListChiTietDatMon1() {
        return listChiTietDatMon1;
    }

    public void setListChiTietDatMon1(List<ChiTietDatMon> listChiTietDatMon1) {
        this.listChiTietDatMon1 = listChiTietDatMon1;
    }

    public List<ChiTietDatMon> getListChiTietDatMon2() {
        return listChiTietDatMon2;
    }

    public void setListChiTietDatMon2(List<ChiTietDatMon> listChiTietDatMon2) {
        this.listChiTietDatMon2 = listChiTietDatMon2;
    }

    public List<HopDongDichVu> getListTTBanTiec() {
        return listTTBanTiec;
    }

    public void setListTTBanTiec(List<HopDongDichVu> listTTBanTiec) {
        this.listTTBanTiec = listTTBanTiec;
    }

    public List<ChiTietDichVu> getListChiTietBanTiec() {
        return listChiTietBanTiec;
    }

    public void setListChiTietBanTiec(List<ChiTietDichVu> listChiTietBanTiec) {
        this.listChiTietBanTiec = listChiTietBanTiec;
    }

    public List<HopDongDichVu> getListTTSanKhau() {
        return listTTSanKhau;
    }

    public void setListTTSanKhau(List<HopDongDichVu> listTTSanKhau) {
        this.listTTSanKhau = listTTSanKhau;
    }

    public List<ChiTietDichVu> getListChiTietSanKhau() {
        return listChiTietSanKhau;
    }

    public void setListChiTietSanKhau(List<ChiTietDichVu> listChiTietSanKhau) {
        this.listChiTietSanKhau = listChiTietSanKhau;
    }

    public List<HopDongDichVu> getListTTCong() {
        return listTTCong;
    }

    public void setListTTCong(List<HopDongDichVu> listTTCong) {
        this.listTTCong = listTTCong;
    }

    public List<ChiTietDichVu> getListChiTietCong() {
        return listChiTietCong;
    }

    public void setListChiTietCong(List<ChiTietDichVu> listChiTietCong) {
        this.listChiTietCong = listChiTietCong;
    }

    public List<HopDongDichVu> getListNgheThuat() {
        return listNgheThuat;
    }

    public void setListNgheThuat(List<HopDongDichVu> listNgheThuat) {
        this.listNgheThuat = listNgheThuat;
    }

    public List<ChiTietDichVu> getListChiTietNgheThuat() {
        return listChiTietNgheThuat;
    }

    public void setListChiTietNgheThuat(List<ChiTietDichVu> listChiTietNgheThuat) {
        this.listChiTietNgheThuat = listChiTietNgheThuat;
    }

    public List<DichVuDiKemModel> getListDichVuDiKem() {
        return listDichVuDiKem;
    }

    public void setListDichVuDiKem(List<DichVuDiKemModel> listDichVuDiKem) {
        this.listDichVuDiKem = listDichVuDiKem;
    }

    public List<ChiTietDichVuDiKem> getListChiTietDichVuDiKem() {
        return listChiTietDichVuDiKem;
    }

    public void setListChiTietDichVuDiKem(List<ChiTietDichVuDiKem> listChiTietDichVuDiKem) {
        this.listChiTietDichVuDiKem = listChiTietDichVuDiKem;
    }
    
    
    

   
    
}
