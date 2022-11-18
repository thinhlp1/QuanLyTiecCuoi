/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.ChiTietDatMon;
import com.happywedding.model.HoaDon;
import com.happywedding.model.MonAn;
import com.happywedding.view.manage.ChiPhiPhatSinh;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HoaDonDAO {

    private final String INSERT_HOADON = "INSERT HoaDon ( MaHD,NgayLap,MaNV,TrangThai ) VALUES ( ?,?,?, 0 )";
    private final String UPDATE_HOADOLN = "UPDATE HoaDon SET TrangThai = 1 WHERE MaHD = ?";
    private final String SELECT_HOADON = "SELECT MaHoaDon,hd.MaHD,hd.NgayLap,nv.MaNV,HoTen,hdd.TienCoc,\n"
            + "( SELECT SUM(ChiPhi) FROM ChiPhiPhatSinh WHERE hd.MaHD =? ) AS ChiPhiPhatSinh, hdd.TongTien,hd.TrangThai\n"
            + "FROM HoaDon hd INNER JOIN HopDong hdd ON hd.MaHD = hdd.MaHD\n"
            + "INNER JOIN NhanVien nv ON hd.MaNV = nv.MaNV\n";
            

    private final String INSERT_CHIPHIPHATSINH = "INSERT ChiPhiPhatSinh (MaHD,MaPLDV,ChiPhi,LyDo) VALUES (?,?,?,?)";
    private final String SELECT_CHIPHIPHATSINH = "SELECT * FROM ChiPhiPhatSinh WHERE MaHD = ? AND MaPLDV = ?";

    public boolean insertHoaDon(HoaDon hd) {
        int rs = JDBCHelper.executeUpdate(INSERT_HOADON, hd.getMaHD(), hd.getNgayLap(), hd.getMaNV());
        return rs > 0;
    }

    public boolean updateHoaDon(long chiPhiPhatSinh, String maHD) {
        int rs = JDBCHelper.executeUpdate(UPDATE_HOADOLN, chiPhiPhatSinh, maHD);
        return rs > 0;
    }

    public HoaDon selectHoaDon(String maHD) {
        List<HoaDon> list = selectHoaDon(SELECT_HOADON, maHD);
        return list.size() > 0 ? list.get(0) : null;
    }

    public boolean insertChiPhiPhatSinh(String maHD, String maDv, long chiPhi, String lyDo) {
        int rs = JDBCHelper.executeUpdate(INSERT_CHIPHIPHATSINH, maHD, maDv, chiPhi, lyDo);
        return rs > 0;
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

    private HoaDon readHoaDonFromResultSet(ResultSet rs) throws SQLException {
        HoaDon hd = new HoaDon();
        hd.setMaHD(rs.getString("MaHD"));
        hd.setMaHoaDon(rs.getInt("MaHoaDon"));
        hd.setTenNV(rs.getString("HoTen"));
        hd.setMaNV(rs.getString("MaNV"));
        hd.setNgayLap(rs.getDate("NgayLap"));
        hd.setTienCoc(rs.getLong("TienCoc"));
        hd.setPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        hd.setTongTien(rs.getLong("TongTien"));
        hd.setTrangTha(rs.getInt("TrangThai"));

        return hd;
    }

}
