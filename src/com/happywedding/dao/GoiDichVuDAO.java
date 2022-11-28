/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.ChiTietDichVu;
import com.happywedding.model.GoiDichVu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class GoiDichVuDAO {

    private final String SELECT_GOIDICHVU_BY_MADV = "SELECT * FROM GoiDichVu WHERE MaDV = ? ";

    private final String SELECT_CHITIETGOI_NOCUSTOM = "SELECT  ct.MaCSVC,csvc.TenCSVC, goi.MaDV,csvc.GiaThue AS ChiPhi,ct.GhiChu FROM ChiTietGoiDichVu  ct\n"
            + "INNER JOIN CoSoVatChat csvc ON ct.MaCSVC = csvc.MaCSVC\n"
            + "INNER JOIN GoiDichVu goi ON goi.MaGoi = ct.MaGoi\n"
            + "INNER JOIN DanhMucCon dmc ON dmc.MaDMC = csvc.MaDMC \n"
            + "WHERE ct.MaGoi = ? AND csvc.MaDMC = ?";

    private final String SELECT_CHITIETGOI_CUSTOM = "SELECT  ct.MaCSVC,csvc.TenCSVC, goi.MaDV,ct.ChiPhi,ct.GhiChu FROM ChiTietGoiDichVu  ct\n"
            + "INNER JOIN CoSoVatChat csvc ON ct.MaCSVC = csvc.MaCSVC\n"
            + "INNER JOIN GoiDichVu goi ON goi.MaGoi = ct.MaGoi\n"
            + "INNER JOIN DanhMucCon dmc ON dmc.MaDMC = csvc.MaDMC \n"
            + "WHERE ct.MaGoi = ? AND csvc.MaDMC = ?";

    public List<GoiDichVu> selectGoiDichVu(String maDV) {
        return selectGoiDichVu(SELECT_GOIDICHVU_BY_MADV, maDV);
    }

    public ChiTietDichVu selectChiTietGoiDichVuNoCustom(String maGoi, String maDMC) {
         List<ChiTietDichVu> list = selectChiTietGoiDichVu(SELECT_CHITIETGOI_NOCUSTOM, maGoi,maDMC);
        return list.size() > 0 ? list.get(0) : null;
    }
    
     public ChiTietDichVu selectChiTietGoiDichVuCustom(String maGoi, String maDMC) {
         List<ChiTietDichVu> list = selectChiTietGoiDichVu(SELECT_CHITIETGOI_CUSTOM, maGoi,maDMC);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List selectGoiDichVu(String sql, Object... args) {
        List<GoiDichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    GoiDichVu model = readGoiDichVuFromResultSet(rs);
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

    private GoiDichVu readGoiDichVuFromResultSet(ResultSet rs) throws SQLException {
        GoiDichVu goi = new GoiDichVu();
        goi.setMaDV(rs.getString("MaDV"));
        goi.setMaGoi(rs.getString("MaGoi"));
        goi.setTenGoi(rs.getString("TenGoi"));
        goi.setChiPhi(rs.getLong("ChiPhi"));
        goi.setGhiChu(rs.getString("GhiChu"));
        goi.setHinhAnh(rs.getString("HinhAnh"));
        return goi;

    }

    private List selectChiTietGoiDichVu(String sql, Object... args) {
        List<ChiTietDichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ChiTietDichVu model = readChiTietGoiDichVuFromResultSet(rs);
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

    private ChiTietDichVu readChiTietGoiDichVuFromResultSet(ResultSet rs) throws SQLException {
        ChiTietDichVu ctdv = new ChiTietDichVu();
        ctdv.setMaDV(rs.getString("MaDV"));
        ctdv.setMaCSVC(rs.getString("MaCSVC"));
        ctdv.setTenCSVC(rs.getString("TenCSVC"));
        ctdv.setChiPhi(rs.getLong("ChiPhi"));
        ctdv.setGhiChu(rs.getString("GhiChu"));
        return ctdv;

    }

}
