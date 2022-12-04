/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKeDAO {

    private final String THONG_KE_DOANH_THU_NAM = "EXEC thongKeDoanhThuNam";
    private final String THONG_KE_DOANH_THU_THANG = "EXEC thongKeDoanhThuThang @Nam = ?";
    
    public List<Object[]> thongKeDoanhThuNam() {
        List<Object[]> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.executeQuery(THONG_KE_DOANH_THU_NAM);
            while (rs.next()) {
                 Object[] model = {
                     rs.getInt("Nam"),
                     rs.getInt("SoLuongHopDong"),
                     rs.getLong("DoanhThuThapNhat"),
                     rs.getLong("DoanhThuCaoNhat"),
                     rs.getLong("TongDoanhThu")
                 };
                 list.add(model);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
     public List<Object[]> thongKeDoanhThuThang(int year) {
        List<Object[]> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.executeQuery(THONG_KE_DOANH_THU_THANG,year);
            while (rs.next()) {
                 Object[] model = {
                     rs.getInt("Thang"),
                     rs.getInt("SoLuongHopDong"),
                     rs.getLong("DoanhThuThapNhat"),
                     rs.getLong("DoanhThuCaoNhat"),
                     rs.getLong("TongDoanhThu")
                 };
                 list.add(model);
            }
        } catch (Exception e) {
        }
        return list;
    }

}