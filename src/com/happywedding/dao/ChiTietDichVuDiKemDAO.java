/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.ChiTietDichVuDiKem;
import com.happywedding.model.HopDongDichVuDiKem;
import com.happywedding.view.manage.DichVuDiKem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChiTietDichVuDiKemDAO {

    private final String SELECT_HDDICHVUDIKEM = "SELECT MaHD,ChiPhi,GhiChu,( SELECT SUM(ChiPhiPhatSinh)  FROM ChiTietDichVuDiKem WHERE MaHD = ? ) AS ChiPhiPhatSinh FROM HopDongDichVuDiKem \n"
            + "WHERE MaHD = ?";

    private final String INSERT_HDDICHVUDIKEM = "INSERT HopDongDichVuDiKem( MaHD,ChiPhi,ChiPhiPhatSinh,GhiChu ) VALUES( ?,?,?,? )";

    private final String UPDATE_HDDICHVUDIKEM = "UPDATE HopDongDichVuDiKem \n"
            + "SET ChiPhi = ?,ChiPhiPhatSinh = ?, GhiChu = ?\n"
            + "WHERE MaHD = ?";
    
    

    private final String INSERT_CHITIETDICHVUDIKEM = "INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhiPhatSinh, ChiPhi) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE_ALL_CHITIETDICHVUDIKEM = "DELETE ChiTietDichVuDiKem WHERE MaHD = ?";
    private final String SELECT_CHITIETDICHVUDIKEM = "SELECT MaHD,dv.MaDV,dv.TenDV,dv.Gia AS ChiPhi,GhiChu,ChiPhiPhatSinh  FROM ChiTietDichVuDiKem ct\n"
            + "INNER JOIN DichVuDiKem  dv ON ct.MaDV = dv.MaDV\n"
            + "WHERE MaHD = ?";
    
    
     public boolean insertHopDongDichVuDiKem(HopDongDichVuDiKem hd) {
        int rs = JDBCHelper.executeUpdate(INSERT_HDDICHVUDIKEM, hd.getMaHD(),  hd.getChiPhi(), hd.getChiPhiPhatSinh(), hd.getGhiChu());
        return rs > 0;
    }
    
      public boolean updateHopDongDichVuDiKem(HopDongDichVuDiKem hd) {
        int rs = JDBCHelper.executeUpdate(UPDATE_HDDICHVUDIKEM,  hd.getChiPhi(), hd.getChiPhiPhatSinh(), hd.getChiPhi(), hd.getMaHD());
        return rs > 0;
    }
      
        public HopDongDichVuDiKem selectHopDongDichVuDiKem(String maHD) {
       List<HopDongDichVuDiKem> list = selectHopDongDichVuDiKem(SELECT_HDDICHVUDIKEM, maHD, maHD);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    /*
    thêm vào bảng ChiTietDichVuDiKem
     */
    public boolean insertChiTietDichVuDiKem(ChiTietDichVuDiKem ct) {
        int rs = JDBCHelper.executeUpdate(INSERT_CHITIETDICHVUDIKEM, ct.getMaHD(), ct.getMaDV(), ct.getGhiChu(), ct.getChiPhiPhatSinh(), ct.getChiPhi());
        return rs > 0;
    }

    public boolean removeAllChiTietDichVuDiKem(String maHD) {
        int rs = JDBCHelper.executeUpdate(DELETE_ALL_CHITIETDICHVUDIKEM, maHD);
        return rs > 0;
    }

    public List<ChiTietDichVuDiKem> selectChiTietDichVuDiKem(String maHD) {
        return selectChiTietDichVuDiKem(SELECT_CHITIETDICHVUDIKEM, maHD);
    }

    /*
    lấy các dịch vụ đi kèm có trong hợp đồng
     */
    public List<DichVuDiKem> selectDichVuDiKemInHD(String maHD) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
    lấy các dịch vụ đi kèm ko có trong hợp đồng
     */
    public List<DichVuDiKem> selectDichVuDiKemNotinHopDong(String maHD) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
     private List selectHopDongDichVuDiKem(String sql, Object... args) {
        List<HopDongDichVuDiKem> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HopDongDichVuDiKem model = readHopDongDichVuDiKemFromResultSet(rs);
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

    private HopDongDichVuDiKem readHopDongDichVuDiKemFromResultSet(ResultSet rs) throws SQLException {
        HopDongDichVuDiKem ctdv = new HopDongDichVuDiKem();

        ctdv.setMaHD(rs.getString("MaHD"));
        ctdv.setChiPhi(rs.getLong("ChiPhi"));
        ctdv.setChiPhiPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        ctdv.setGhiChu(rs.getString("GhiChu"));
        return ctdv;

    }

    private List selectChiTietDichVuDiKem(String sql, Object... args) {
        List<ChiTietDichVuDiKem> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ChiTietDichVuDiKem model = readChiTietDichVuDiKemFromResultSet(rs);
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

    private ChiTietDichVuDiKem readChiTietDichVuDiKemFromResultSet(ResultSet rs) throws SQLException {
        ChiTietDichVuDiKem ctdv = new ChiTietDichVuDiKem();

        ctdv.setMaHD(rs.getString("MaHD"));
        ctdv.setMaDV(rs.getString("MaDV"));
        ctdv.setChiPhi(rs.getLong("ChiPhi"));
        ctdv.setChiPhiPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        ctdv.setGhiChu(rs.getString("GhiChu"));
        return ctdv;

    }
    
    

}
