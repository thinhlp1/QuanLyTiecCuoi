/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happywedding.dao;

import com.happywedding.helper.DateHelper;
import com.happywedding.model.ChiTietPhanCong;

/**
 *
 * @author ACER
 */
public class testDAO {

    public static void main(String[] args) {
        HopDongDAO dao = new HopDongDAO();
        PhanCongDAO pcDAO = new PhanCongDAO();
        ChiTietPhanCong ctpc = new ChiTietPhanCong();

        ctpc.setMaPC(7);
        ctpc.setMaNV("NV002");
        ctpc.setNgayPhanCong(DateHelper.toDate("01/01/2022", "dd/MM/yyyy"));
        ctpc.setThoiGianBatDau("05:00:00");
        ctpc.setThoiGianKetThuc("23:00:00");
        
       pcDAO.insertChiTietPhanCong(ctpc);
//        pcDAO.deletePhanCong("4");

//        PhanCong c = new PhanCong();
//        c.setMaHD("HD004");
//        c.setMaNguoiPC("NV001");
//        pcDAO.insertPhanCong(c);
    }
}
