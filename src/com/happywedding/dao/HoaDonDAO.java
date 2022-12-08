/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.ChiPhiPhatSinhModel;
import com.happywedding.model.ChiTietDatMon;
import com.happywedding.model.HoaDon;
import com.happywedding.model.MonAn;
import com.happywedding.view.manage.ChiPhiPhatSinh;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HoaDonDAO {

    private final String INSERT_HOADON = "INSERT HoaDon ( MaHD,NgayLap,MaNV,TrangThai ) VALUES ( ?,?,?, 0 )";
    private final String UPDATE_HOADOLN = "UPDATE HoaDon SET TrangThai = 1,NgayLapLan2 =?,MaNLLan2 = ?,TongTien = ? WHERE MaHD = ?";
    private final String SELECT_HOADON = "SELECT MaHoaDon,hd.MaHD,hd.NgayLap,hd.NgayLapLan2,nv.MaNV,HoTen,hdd.TienCoc,hd.MaNLLan2, ( SELECT HoTen FROM NhanVien WHERE MaNV = hd.MaNLLan2 ) AS HoTenNguoiLap2,\n"
            + "( SELECT SUM(ChiPhi) FROM ChiPhiPhatSinh WHERE hd.MaHD = hdd.MaHD ) AS ChiPhiPhatSinh, hd.TongTien,hd.TrangThai\n"
            + "FROM HoaDon hd INNER JOIN HopDong hdd ON hd.MaHD = hdd.MaHD\n"
            + "INNER JOIN NhanVien nv ON hd.MaNV = nv.MaNV";

    private final String SELECT_HOADON_BYID = "SELECT MaHoaDon,hd.MaHD,hd.NgayLap,hd.NgayLapLan2,nv.MaNV,HoTen,hdd.TienCoc,hd.MaNLLan2, ( SELECT HoTen FROM NhanVien WHERE MaNV = hd.MaNLLan2 ) AS HoTenNguoiLap2,\n"
            + "( SELECT SUM(ChiPhi) FROM ChiPhiPhatSinh WHERE hd.MaHD = hdd.MaHD ) AS ChiPhiPhatSinh, hd.TongTien,hd.TrangThai\n"
            + "FROM HoaDon hd INNER JOIN HopDong hdd ON hd.MaHD = hdd.MaHD\n"
            + "INNER JOIN NhanVien nv ON hd.MaNV = nv.MaNV WHERE hdd.MaHD = ?\n";

    private final String INSERT_CHIPHIPHATSINH = "INSERT ChiPhiPhatSinh (MaHD,MaDV,ChiPhi,LyDo) VALUES (?,?,?,?)";
    private final String SELECT_CHIPHIPHATSINH = "SELECT * FROM ChiPhiPhatSinh WHERE MaHD = ? ";
    private final String SELECT_CHIPHIPHATSINH_BYMADV = "SELECT * FROM ChiPhiPhatSinh WHERE MaHD = ? AND MaDV =?";

    public boolean insertHoaDon(HoaDon hd) {
        int rs = JDBCHelper.executeUpdate(INSERT_HOADON, hd.getMaHD(), hd.getNgayLap(), hd.getMaNV());
        return rs > 0;
    }

    public boolean updateHoaDon(String maHD, Date date, String maNL, long tongTien) {
        int rs = JDBCHelper.executeUpdate(UPDATE_HOADOLN, date, maNL,tongTien, maHD );
        return rs > 0;
    }

    public List<HoaDon> selectHoaDon() {
        return selectHoaDon(SELECT_HOADON);

    }

    public HoaDon selectByID(String maHD) {
        List<HoaDon> list = selectHoaDon(SELECT_HOADON_BYID, maHD);
        return list.size() > 0 ? list.get(0) : null;
    }

    public boolean insertChiPhiPhatSinh(ChiPhiPhatSinhModel cpps) {
        int rs = JDBCHelper.executeUpdate(INSERT_CHIPHIPHATSINH, cpps.getMaHD(), cpps.getMaDV(), cpps.getChiPhi(), cpps.getLyDo());
        return rs > 0;
    }

    public List selectChiPhiPhatSinh(String maHD) {
        return selectChiPhiPhatSinh(SELECT_CHIPHIPHATSINH, maHD);
    }

    public ChiPhiPhatSinhModel selectChiPhiPhatSinhByMaDV(String maHD, String maDV) {
        List<ChiPhiPhatSinhModel> list = selectChiPhiPhatSinh(SELECT_CHIPHIPHATSINH_BYMADV, maHD, maDV);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List selectHoaDon(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HoaDon model = readHoaDonFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private List selectChiPhiPhatSinh(String sql, Object... args) {
        List<ChiPhiPhatSinhModel> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ChiPhiPhatSinhModel model = readChiPhiPhatSinh(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private HoaDon readHoaDonFromResultSet(ResultSet rs) throws SQLException {
        HoaDon hd = new HoaDon();
        hd.setMaHD(rs.getString("MaHD"));
        hd.setMaHoaDon(rs.getInt("MaHoaDon"));
        hd.setTenNV(rs.getString("HoTen"));
        hd.setMaNV(rs.getString("MaNV"));
        hd.setNgayLap(rs.getDate("NgayLap"));
        hd.setNgayLapLan2(rs.getDate("NgayLapLan2"));
        hd.setMaNLLan2(rs.getString("MaNLLan2"));
        hd.setTenNLLan2(rs.getString("HoTenNguoiLap2"));
        hd.setTienCoc(rs.getLong("TienCoc"));
        hd.setPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        hd.setTongTien(rs.getLong("TongTien"));
        hd.setTrangTha(rs.getInt("TrangThai"));

        return hd;
    }

    private ChiPhiPhatSinhModel readChiPhiPhatSinh(ResultSet rs) throws SQLException {
        ChiPhiPhatSinhModel cpps = new ChiPhiPhatSinhModel();

        cpps.setMaHD(rs.getString("MaHD"));
        cpps.setMaDV(rs.getString("MaDV"));
        cpps.setChiPhi(rs.getLong("ChiPhi"));
        cpps.setLyDo(rs.getString("LyDo"));

        return cpps;
    }

}
