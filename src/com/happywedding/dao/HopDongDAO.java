/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.ChiTietDichVu;
import com.happywedding.model.DichVuDuaDon;
import com.happywedding.model.HopDong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HopDongDAO extends AbstractDAO<HopDong> {

    private final String INSERT = "INSERT INTO dbo.HopDong ( MaHD,MaNL,SoLuongBan,Sanh,NgayLap,NgayDuyet,MaND,MaKH,NgayToChuc,ThoiGianBatDau,ThoiGianKetThuc,TrangThai,The,TienCoc,ChiPhiPhatSinh,TongTien) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String DELETE = "DELETE dbo.HopDong WHERE MaHD = ?";
    private final String UPDATE = "UPDATE dbo.HopDong SET MaNL = ?,SoLuongBan = ?,Sanh = ?, NgayLap = ?, NgayDuyet = ?, MaND = ?, MaKH = ?, NgayToChuc = ?,ThoiGianBatDau = ?, ThoiGianKetThuc = ?, TrangThai = ?, the = ?, TienCoc = ?,ChiPhiPhatSinh = ?, TongTien = ? where MaHD = ? ";
    private final String SECLECT_ALL = "SELECT * FROM dbo.HopDong";
    private final String SECLECT_BY_ID = "SELECT * FROM dbo.HopDong WHERE MaHD = ? ";
    private final String UPDATE_TRANGTHAI = "UPDATE dbo.HopDong SET TrangThai = ? WHERE MaHD = ?";

    @Override
    public boolean insert(HopDong entity) {
        int rs = JDBCHelper.executeUpdate(INSERT, entity.getMaHD(), entity.getMaNL(), entity.getSoLuongBan(), entity.getSanh(), entity.getNgayLap(), entity.getNgayDuyet(), entity.getMaND(), entity.getMaKH(), entity.getNgayToChuc(), entity.getThoiGianBatDau(), entity.getThoiGianKetThuc(), entity.getTrangThai(), entity.getThue(), entity.getTienCoc(), entity.getChiPhiPhatSinh(), entity.getTongTien());
        return rs > 0;
    }

    @Override
    public boolean update(HopDong entity) {
        int rs = JDBCHelper.executeUpdate(UPDATE, entity.getMaHD(), entity.getMaNL(), entity.getSoLuongBan(), entity.getSanh(), entity.getNgayLap(), entity.getNgayDuyet(), entity.getMaND(), entity.getMaKH(), entity.getNgayToChuc(), entity.getThoiGianBatDau(), entity.getThoiGianKetThuc(), entity.getTrangThai(), entity.getThue(), entity.getTienCoc(), entity.getChiPhiPhatSinh(), entity.getTongTien());
        return rs > 0;
    }

    @Override
    public void delete(String id) {
        int rs = JDBCHelper.executeUpdate(DELETE, id);
    }

    @Override
    public List<HopDong> select() {

        return select(SECLECT_ALL);

    }

    private List select(String sql, Object... args) {
        List<HopDong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HopDong hopDong = readFromResultSet(rs);
                    list.add(hopDong);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    @Override
    public HopDong findById(String id) {
        List<HopDong> list = select(SECLECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public void danhDauXoa(String maHD, String MaTT) {
        int rs = JDBCHelper.executeUpdate(UPDATE_TRANGTHAI, MaTT);
    }

    private HopDong readFromResultSet(ResultSet rs) throws SQLException {
        HopDong hopDong = new HopDong();

        hopDong.setMaHD(rs.getString("MaHD"));
        hopDong.setMaNL(rs.getString("MaNL"));
        hopDong.setSoLuongBan(rs.getLong("SoLuongBan"));
        hopDong.setSanh(rs.getString("Sanh"));
        hopDong.setNgayLap(rs.getDate("NgayLap"));
        hopDong.setNgayDuyet(rs.getDate("NgayDuyet"));
        hopDong.setMaND(rs.getString("MaND"));
        hopDong.setMaKH(rs.getInt("MaKH"));
        hopDong.setNgayToChuc(rs.getDate("NgayToChuc"));
        hopDong.setThoiGianBatDau(rs.getString("ThoiGianBatDau"));
        hopDong.setThoiGianKetThuc(rs.getString("ThoiGianKetThuc"));
        hopDong.setTrangThai(rs.getString("TrangThai"));
        hopDong.setThue(rs.getLong("The"));
        hopDong.setTienCoc(rs.getLong("TienCoc"));
        hopDong.setChiPhiPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        hopDong.setTongTien(rs.getLong("TongTien"));
        return hopDong;
    }
}
