/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.ChiTietPhanCong;
import com.happywedding.model.HopDong;
import com.happywedding.model.NhanVien;
import com.happywedding.model.PhanCong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
// phân công - insert, update, select, select by id, readform
// chi tiết phân công - insert, update, delete, select, select by id, readform
public class PhanCongDAO {


    private final String INSERT_PHANCONG = "INSERT INTO dbo.PhanCong (MaHD,MaNguoiPC)VALUES(?,?)";
    private final String DELETE_PHANCONG = "DELETE dbo.PhanCong WHERE MaPC = ?";
    private final String UPDATE_PHANCONG = "UPDATE dbo.PhanCong SET MaHD = ?, MaNguoiPC = ? WHERE MaPC = ? ";
    private final String SECLECT_ALL_PHANCONG = "SELECT * FROM dbo.PhanCong";
    private final String SECLECT_BYID_PHANCONG = "SELECT * FROM dbo.PhanCong WHERE MaPC = ?";

    private final String INSERT_CHITIETPHANCONG = "INSERT INTO dbo.ChiTietPhanCong(MaPC,MaNV,NgayPC,GioBatDau,GioKetThuc)VALUES(?,?,?,?,?)";
    private final String DELETE_CHITIETPHANCONG = "DELETE dbo.ChiTietPhanCong WHERE MaPC = ?";
    private final String UPDATE_CHITIETPHANCONG = "UPDATE dbo.ChiTietPhanCong SET MaNV = ?, NgayPC = ?, GioBatDau = ?, GioKetThuc = ? WHERE MaPC = ?";
    private final String SECLECT_ALL_CHITIETPHANCONG = "SELECT * FROM dbo.ChiTietPhanCong";
    private final String SECLECT_BYID_CHITIETPHANCONG = "SELECT * FROM dbo.ChiTietPhanCong WHERE MaPC = ?";

    public boolean insertPhanCong(PhanCong pc) {
        int rs = JDBCHelper.executeUpdate(INSERT_PHANCONG, pc.getMaHD(), pc.getMaNguoiPC());
        return rs > 0;
    }

    public boolean updatePhanCong(PhanCong entity) {
        int rs = JDBCHelper.executeUpdate(UPDATE_PHANCONG, entity.getMaHD(), entity.getMaNguoiPC());
        return rs > 0;
    }

    public void deletePhanCong(String id) {
        int rs = JDBCHelper.executeUpdate(DELETE_PHANCONG, id);
    }

    public List<HopDong> selectAllPhanCong() {

        return selectPhanCong(SECLECT_ALL_PHANCONG);

    }

    public PhanCong findById(String id) {
        List<PhanCong> list = selectPhanCong(SECLECT_BYID_PHANCONG, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List selectPhanCong(String sql, Object... args) {
        List<PhanCong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    PhanCong phanCong = readFromResultSetPhanCong(rs);
                    list.add(phanCong);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private PhanCong readFromResultSetPhanCong(ResultSet rs) throws SQLException {
        PhanCong phanCong = new PhanCong();
        phanCong.setMaPC(rs.getInt("MaPC"));
        phanCong.setMaHD(rs.getString("MaHD"));
        phanCong.setMaNguoiPC(rs.getString("MaNguoiPC"));
        return phanCong;
    }

    public boolean insertChiTietPhanCong(ChiTietPhanCong entity) {

        int rs = JDBCHelper.executeUpdate(INSERT_CHITIETPHANCONG, entity.getMaPC(), entity.getMaNV(), entity.getNgayPhanCong(), entity.getThoiGianBatDau(), entity.getThoiGianKetThuc());
        return rs > 0;

    }

    public boolean updateChiTietPhanCong(ChiTietPhanCong entity) {
        int rs = JDBCHelper.executeUpdate(UPDATE_CHITIETPHANCONG, entity.getMaNV(), entity.getNgayPhanCong(), entity.getThoiGianBatDau(), entity.getThoiGianKetThuc());
        return rs > 0;
    }

    public void deleteChiTietPhanCong(String id) {
        int rs = JDBCHelper.executeUpdate(DELETE_CHITIETPHANCONG, id);
    }

    public List<ChiTietPhanCong> selectAllChiTietPhanCong() {
        return selectChiTietPhanCong(SECLECT_ALL_CHITIETPHANCONG);
    }

    public ChiTietPhanCong findByIdChiTietPhanCong(String id) {
        List<ChiTietPhanCong> list = selectChiTietPhanCong(SECLECT_BYID_CHITIETPHANCONG, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List selectChiTietPhanCong(String sql, Object... args) {
        List<ChiTietPhanCong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ChiTietPhanCong ctpc = readFromResultSetChiTietPhanCong(rs);
                    list.add(ctpc);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private ChiTietPhanCong readFromResultSetChiTietPhanCong(ResultSet rs) throws SQLException {
        ChiTietPhanCong ChiTietPhanCong = new ChiTietPhanCong();
        ChiTietPhanCong.setMaPC(rs.getInt("MaPC"));
        ChiTietPhanCong.setMaNV(rs.getString("MaNV"));
        ChiTietPhanCong.setNgayPhanCong(rs.getDate("NgayPC"));
        ChiTietPhanCong.setThoiGianBatDau(rs.getString("GioBatDau"));
        ChiTietPhanCong.setThoiGianKetThuc(rs.getString("GioKetThuc"));
        return ChiTietPhanCong;
    }
}
