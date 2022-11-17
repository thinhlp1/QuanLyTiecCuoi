/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.ChiTietDatMon;
import com.happywedding.model.DichVuDatMon;
import com.happywedding.model.HopDongDichVu;
import com.happywedding.model.MonAn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChiTietDatMonDAO {

    private final String INSERT_DICHVUDATMON = "INSERT DichVuDatMon (MaHD,MaTD,ChiPhi, GhiChu) VALUES (?,?,?,?)";
    private final String UPDATE_DICHVUDATMON = "UPDATE DichVuDatMon\n"
            + "SET MaTD = ?, ChiPhi = ?, GhiChu = ?\n"
            + "WHERE MaHD = ?";
    private final String SELECT_DICHVUDATMON = "SELECT MaHD,MaTD,ChiPhi, \n"
            + "( SELECT SUM(ChiPhiPhatSinh)  FROM ChiTietDatMon WHERE MaHD = ?) AS ChiPhiPhatSinh,\n"
            + "GhiChu FROM DichVuDatMon WHERE MaHD = ?";

    private final String SELECT_CHITIETDATMON = "SELECT MaHD,ct.MaMA,TenMA,ct.Gia,ct.ChiPhiPhatSinh,ThuTu,GhiChu FROM ChiTietDatMon ct\n"
            + "	INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA \n"
            + "	WHERE MaHD = ?";
    
    

    /*
    thêm vào bảng dịch vụ đặt món
     */
    public boolean insertDichVuDatMon(DichVuDatMon dvdm) {
        int rs = JDBCHelper.executeUpdate(INSERT_DICHVUDATMON, dvdm.getMaHD(), dvdm.getMaTD(), dvdm.getChiPhi(), dvdm.getGhiChu());
        return rs > 0;
    }

    public boolean updateDichVuDatMon(DichVuDatMon dvdm) {
        int rs = JDBCHelper.executeUpdate(UPDATE_DICHVUDATMON, dvdm.getMaTD(), dvdm.getChiPhi(), dvdm.getGhiChu(), dvdm.getMaHD());
        return rs > 0;
    }

    public DichVuDatMon selectDichVuDatMon(String maHD) {
        List<DichVuDatMon> list = selectDichVuDatMon(SELECT_DICHVUDATMON, maHD);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<ChiTietDatMon> selectDichVuDatMon() {
        List<DichVuDatMon> list = selectDichVuDatMon(, args);
        return null;
    }

    public boolean insertChiTietDatMon(String maHD, String maMA, String thuTu, String ghiChu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean updateChiTietDatMon(String maHD, String maMA, String thuTu, String ghiChu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
    reset lại các món ăn trong ChiTietDatMon
     */
    public boolean removeAllChiTietDatMon(String maHD) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
    lấy các món ăn có trong thực đơn
     */
    public List<MonAn> selectByMaThucDon(String maTD) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
    lấy các món ăn người dùng đặt
     */
    public List<MonAn> selectByMaHD(String maHD) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
    lấy các món ăn ko có trong thực đơn
     */
    public List<MonAn> selectNotinHopDong(String maTD) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
    lấy các món ăn ko có trong thực đơn
     */
    public List<MonAn> selectNotinThucDon(String maHD) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private List selectDichVuDatMon(String sql, Object... args) {
        List<DichVuDatMon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    DichVuDatMon model = readDichVuDatMonFromResultSet(rs);
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

    private List selectChiTietDatMon(String sql, Object... args) {
        List<ChiTietDatMon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ChiTietDatMon model = readChiTietDatMonFromResultSet(rs);
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

    private ChiTietDatMon readChiTietDatMonFromResultSet(ResultSet rs) throws SQLException {
        ChiTietDatMon ctdm = new ChiTietDatMon();

        ctdm.setMaHD(rs.getString("MaHD"));
        ctdm.setMaMA(rs.getString("MaMA"));
        ctdm.setTenMA(rs.getString("TenMA"));
        ctdm.setGia(rs.getLong("Gia"));
        ctdm.setChiPhiPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        ctdm.setThuTu(rs.getInt("ThuTu"));
        return ctdm;
    }

    private DichVuDatMon readDichVuDatMonFromResultSet(ResultSet rs) throws SQLException {
        DichVuDatMon dvdm = new DichVuDatMon();

        dvdm.setMaHD(rs.getString("MaHD"));
        dvdm.setMaTD(rs.getString("MaTD"));
        dvdm.setChiPhi(rs.getLong("ChiPhi"));
        dvdm.setChiPhiPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        dvdm.setGhiChu(rs.getString("TenCSVC"));
        return dvdm;

    }

}
