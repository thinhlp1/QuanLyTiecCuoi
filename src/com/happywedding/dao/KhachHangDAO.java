/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KhachHangDAO extends AbstractDAO<KhachHang> {

    private final String INSERT_KHACHHANG = "INSERT INTO KhachHang ( MaHD,HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( ?,?, ?, ?, ?, ?, ?)";

    private final String UPDATE_KHACHANG = "UPDATE KhachHang SET HoTen=?, SoDienThoai=?, CCCD=?, DiaChi=?, HoTenCoDau=?, HoTenChuRe=? WHERE \n"
            + "MaKH=?";
    private final String DELELTE_KHACHHANG = "DELETE FROM KhachHang WHERE MaKH=?";
    private final String SELECT_ALL = "SELECT * FROM KhachHang";
    private final String SELECT_LAST = "SELECT TOP 1 * FROM KhachHang ORDER BY MaKH DESC";
    private final String SELECT_BY_ID = "SELECT * FROM KhachHang WHERE MaHD=?";
    private final String SELECT_BY_SDT = "SELECT * FROM KhachHang WHERE SoDienThoai=?";
    public boolean insert(KhachHang khachHang) {
        int rs = JDBCHelper.executeUpdate(INSERT_KHACHHANG,
                khachHang.getMaHD(),
                khachHang.getHoTen(),
                khachHang.getSoDienThoai(),
                khachHang.getCCCD(),
                khachHang.getDiaChi(),
                khachHang.getHoTenCoDau(),
                khachHang.getHoTenChuRe());
        return rs > 0;
    }

    public boolean update(KhachHang khachHang) {
        int rs = JDBCHelper.executeUpdate(UPDATE_KHACHANG,
                khachHang.getHoTen(),
                khachHang.getSoDienThoai(),
                khachHang.getCCCD(),
                khachHang.getDiaChi(),
                khachHang.getHoTenCoDau(),
                khachHang.getHoTenChuRe(),
                khachHang.getMaKH());
        return rs > 0;
    }

    public void delete(String id) {
        int rs = JDBCHelper.executeUpdate(DELELTE_KHACHHANG, id);
    }

    @Override
    public List<KhachHang> select() {
        return select(SELECT_ALL);
    }
    
    public KhachHang findLastKhachHang() {
        List<KhachHang> list = select(SELECT_LAST);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public KhachHang findById(String id) {
        List<KhachHang> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    
     public KhachHang findBySDT(String sdt) {
        List<KhachHang> list = select(SELECT_BY_SDT, sdt);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List select(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    KhachHang khachHang = readFromResultSet(rs);
                    list.add(khachHang);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    // sửa lại
    private KhachHang readFromResultSet(ResultSet rs) throws SQLException {

        KhachHang khachHang = new KhachHang();
        khachHang.setMaHD(rs.getString("MaHD"));
        khachHang.setMaKH(rs.getInt("MaKH"));
        khachHang.setHoTen(rs.getString("HoTen"));
        khachHang.setSoDienThoai(rs.getString("SoDienThoai"));
        khachHang.setCCCD(rs.getString("CCCD"));
        khachHang.setDiaChi(rs.getString("DiaChi"));
        khachHang.setHoTenCoDau(rs.getString("HoTenCoDau"));
        khachHang.setHoTenChuRe(rs.getString("HoTenChuRe"));

        return khachHang;
//
    }

}
