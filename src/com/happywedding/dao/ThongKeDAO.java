/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKeDAO {

    private final String THONG_KE_DOANH_THU_NAM = "EXEC thongKeDoanhThuNam";
    private final String THONG_KE_DOANH_THU_THANG = "EXEC thongKeDoanhThuThang @Nam = ?";
    private final String THONG_KE_THEO_NGAY = "EXEC thongKeDoanhThuNgayTop @top = ?,@nam = ?";
    private final String THONG_KE_THEO_NGAY_SL = "EXEC thongKeDoanhThuNgayTopSL @top = ?,@nam = ?";

    private final String THONG_DICHVU_NAM_HD = "SELECT IIF(hoadon.TrangThai = 0, YEAR(hd.NgayLap),YEAR(hoadon.NgayLapLan2)) AS Nam,hd.MaHD,SUM( dvdm.ChiPhi ) AS ChiPhiDatMon ,hddv.ChiPhi as ChiPhiDichVu,hddv.MaDV ,dv.TenDV,SUM( DISTINCT dk.ChiPhi ) as ChiPhiDiKem FROM HopDong hd INNER JOIN DichVuDatMon dvdm ON hd.MaHD = dvdm.MaHD\n"
            + "INNER JOIN HopDongDichVuDiKem dk ON hd.MaHD = dk.MaHD\n"
            + "INNER JOIN HopDongDichVu hddv ON hd.MaHD = hddv.MaHD\n"
            + "INNER JOIN HoaDon hoadon ON hd.MaHD = hoadon.MaHD\n"
            + "INNER JOIN DichVu dv ON dv.MaDV = hddv.MaDV\n"
            + "WHERE IIF(hoadon.TrangThai = 0, YEAR(hd.NgayLap),YEAR(hoadon.NgayLapLan2)) = ? AND hd.MaHD = ?\n"
            + "GROUP BY  hddv.ChiPhi,hddv.MaDV ,dv.TenDV,dk.ChiPhi ,hd.MaHD,hoaDon.MaHD,IIF(hoadon.TrangThai = 0, YEAR(hd.NgayLap),YEAR(hoadon.NgayLapLan2))";

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
            rs = JDBCHelper.executeQuery(THONG_KE_DOANH_THU_THANG, year);
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

    public List<Object[]> thongKeDoanhTheoNgay(int top, int nam) {
        List<Object[]> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.executeQuery(THONG_KE_THEO_NGAY, top, nam);
            while (rs.next()) {
                Object[] model = {
                    rs.getDate("Ngay"),
                    rs.getInt("SoLuongHopDong"),
                    rs.getLong("DoanhThuThapNhat"),
                    rs.getLong("DoanhThuCaoNhat"),
                    rs.getLong("TongDoanhThu")
                };
                list.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Object[]> thongKeDoanhTheoNgaySL(int sl, int nam) {
        List<Object[]> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.executeQuery(THONG_KE_THEO_NGAY_SL, sl, nam);
            while (rs.next()) {
                Object[] model = {
                    rs.getDate("Ngay"),
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

    public List<Object[]> thongKeDichVuTheoNamHD(int nam, String maHD) {
        List<Object[]> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.executeQuery(THONG_DICHVU_NAM_HD, nam, maHD);
            int i = 0;
            int namHD;
            long chiPhiDatMon = 0;
            long chiPhiDiKem = 0;
            long ttCong = 0;
            long ttSanKhau = 0;
            long ttBan = 0;
            long ngheThuat = 0;

            while (rs.next()) {
                
                chiPhiDatMon = rs.getLong("ChiPhiDatMon");
                chiPhiDiKem = rs.getLong("ChiPhiDiKem");
                switch (i) {
                    case 0:
                        ngheThuat = rs.getLong("ChiPhiDichVu");
                        break;
                    case 1:
                        ttBan = rs.getLong("ChiPhiDichVu");
                        break;
                    case 2:
                        ttCong = rs.getLong("ChiPhiDichVu");
                        break;
                    case 3:
                        ttSanKhau = rs.getLong("ChiPhiDichVu");
                        break;
                }
                i++;

            }
            list.add(new Object[]{nam,i,chiPhiDatMon,chiPhiDiKem,ngheThuat,ttBan,ttCong,ttSanKhau});
        } catch (Exception e) {
        }
        return list;
    }
    
      

    public int getFirtYear() {

        ResultSet rs = null;
        try {
            rs = JDBCHelper.executeQuery("SELECT Top 1 YEAR(NgayLap) AS Nam FROM HopDong ORDER BY NgayLap ASC");
            while (rs.next()) {
                return rs.getInt("Nam");
            }
        } catch (Exception e) {
        }
        return Calendar.getInstance().get(Calendar.YEAR);

    }

}
