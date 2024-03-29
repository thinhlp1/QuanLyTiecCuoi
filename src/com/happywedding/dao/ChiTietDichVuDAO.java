/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.ChiTietDichVu;
import com.happywedding.model.HopDongDichVu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChiTietDichVuDAO {

    private final String INSERT_HOPDONGDICHVU = "INSERT HopDongDichVu (MaHD, MaDV,MaGoi ,ChiPhi, GhiChu) VALUES (?, ?, ? ,?, ?)";
    private final String UPDATE_HOPDONGDICHVU = "UPDATE HopDongDichVu\n"
            + "SET MaGoi = ?, ChiPhi = ?,  GhiChu = ?\n"
            + "WHERE MaHD = ? AND MaDV = ?";
    private final String SELECT_DICHVU = "SELECT MaHD,MaDV,MaGoi,ChiPhi, \n"
            + "\n"
            + "(SELECT SUM(ChiPhiPhatSinh) AS ChiPhiPhatSinh FROM HopDongDichVu hddv \n"
            + "INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD\n"
            + "WHERE ct.MaHD = ? AND  hddv.MaDV = ? AND ct.MaDV = ?   ) AS ChiPhiPhatSinh ,\n"
            + "\n"
            + "GhiChu FROM HopDongDichVu hddv  \n"
            + "WHERE hddv.MaHD = ? AND hddv.MaDV = ?";
    
   

    private final String CHECK_HOPDONGDICHVU = "SELECT * FROM HopDongDichVu WHERE MaHD = ? AND MaDV = ?";

    private final String INSERT_CHITIETDICHVU = "INSERT ChiTietDichVu (MaHD,MaDV, MaCSVC,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (?, ?, ?, ? ,?, ?)";
    private final String UPDATE_CHITIETDICHVU = "UPDATE ctdv SET MaCSVC = ?, ChiPhi = ?, ChiPhiPhatSinh = ?, GhiChu = ?\n"
            + "FROM ChiTietDichVu ctdv INNER JOIN CoSoVatChat csvc ON ctdv.MaCSVC = csvc.MaCSVC\n"
            + "WHERE MaHD = ? AND MaDV = ? AND csvc.MaDMC = ?";

    private final String SELECT_ALL_CHITIETDICHVU = "SELECT hddv.MaHD,csvc.MaCSVC,csvc.TenCSVC, hddv.MaDV,ct.ChiPhi,ct.ChiPhiPhatSinh,ct.GhiChu FROM HopDongDichVu hddv \n"
            + "INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD\n"
            + "INNER JOIN CoSoVatChat csvc ON csvc.MaCSVC = ct.MaCSVC \n"
            + "WHERE ct.MaHD = ? AND  hddv.MaDV = ? AND ct.MaDV = ? ";

    private final String SELECT_CHITIETDIVU_CUSTOM = "SELECT hddv.MaHD,csvc.MaCSVC,csvc.TenCSVC, hddv.MaDV,ct.ChiPhi,ct.ChiPhiPhatSinh,ct.GhiChu FROM HopDongDichVu hddv \n"
            + "INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD\n"
            + "INNER JOIN CoSoVatChat csvc ON csvc.MaCSVC = ct.MaCSVC \n"
            + "INNER JOIN DanhMucCon dmc ON dmc.MaDMC = csvc.MaDMC \n"
            + "WHERE ct.MaHD = ? AND  hddv.MaDV = ? AND ct.MaDV = ? AND csvc.MaDMC = ? ";

    private final String SELECT_CHITIEDICHVU_NOCUSTOM = "SELECT hddv.MaHD,csvc.MaCSVC,csvc.TenCSVC, hddv.MaDV,csvc.GiaThue AS ChiPhi,ct.ChiPhiPhatSinh,ct.GhiChu FROM HopDongDichVu hddv \n"
            + "INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD\n"
            + "INNER JOIN CoSoVatChat csvc ON csvc.MaCSVC = ct.MaCSVC \n"
            + "INNER JOIN DanhMucCon dmc ON dmc.MaDMC = csvc.MaDMC \n"
            + "WHERE ct.MaHD = ? AND  hddv.MaDV = ? AND ct.MaDV = ? AND csvc.MaDMC = ? ";

    private final String SELECT_SOLUONGBAN = "SELECT distinct  SUM(SoLuongBan) AS SoLuongBan FROM  CoSoVatChat csvc LEFT JOIN ChiTietDichVu ctdv ON csvc.MaCSVC = ctdv.MaCSVC\n"
            + "INNER JOIN HopDong hd ON hd.MaHD = ctdv.MaHD\n"
            + "WHERE csvc.MaCSVC = ? AND NgayToChuc = ?\n"
            + "GROUP BY csvc.MaCSVC";

    private final String SELECT_SOLUONGSUDUNG = "SELECT COUNT(*) AS SoLuongSuDung  FROM  CoSoVatChat csvc LEFT JOIN ChiTietDichVu ctdv ON csvc.MaCSVC = ctdv.MaCSVC\n"
            + "INNER JOIN HopDong hd ON hd.MaHD = ctdv.MaHD\n"
            + "WHERE csvc.MaCSVC = ? AND NgayToChuc = ?\n"
            + "GROUP BY csvc.MaCSVC";
    
     public final String DELETE_ALL_CHITIETDICHVU = "DELETE ChiTietDichVu WHERE MaHD = ? AND MaDV = ?";

    /*
    thêm vào HopDongDichVu
     */
    public boolean insertDichVu(HopDongDichVu ctdv) {

        int rs = JDBCHelper.executeUpdate(INSERT_HOPDONGDICHVU, ctdv.getMaHD(), ctdv.getMaDV(), ctdv.getMaGoi(), ctdv.getChiPhi(), ctdv.getGhiChu());
        return rs > 0;
    }

    public boolean updateDichVu(HopDongDichVu ctdv) {
        int rs = JDBCHelper.executeUpdate(UPDATE_HOPDONGDICHVU, ctdv.getMaGoi(), ctdv.getChiPhi(), ctdv.getGhiChu(), ctdv.getMaHD(), ctdv.getMaDV());
        return rs > 0;
    }

    public HopDongDichVu selectDichVu(String maHD, String maDV) {
        List<HopDongDichVu> list = selectDichVu(SELECT_DICHVU, maHD, maDV, maDV, maHD, maDV);
        return list.size() > 0 ? list.get(0) : null;
    }

    public boolean checkHopDongDichVu(String maHD, String maDV) {
        List<HopDongDichVu> list = selectDichVu(SELECT_DICHVU, maHD, maDV, maDV, maHD, maDV);
        return list.size() > 0;
    }

    /*
    thêm vào bảng ChiTietDichVu
     */
    public boolean insertChiTietDichVy(ChiTietDichVu ctdv) {
        int rs = JDBCHelper.executeUpdate(INSERT_CHITIETDICHVU, ctdv.getMaHD(), ctdv.getMaDV(), ctdv.getMaCSVC(), ctdv.getChiPhi(), ctdv.getChiPhiPhatSinh(), ctdv.getGhiChu());
        return rs > 0;
    }

    public boolean updateChiTietDichVu(ChiTietDichVu ctdv, String maDMC) {
        int rs = JDBCHelper.executeUpdate(UPDATE_CHITIETDICHVU, ctdv.getMaCSVC(), ctdv.getChiPhi(), ctdv.getChiPhiPhatSinh(), ctdv.getGhiChu(), ctdv.getMaHD(), ctdv.getMaDV(), maDMC);
        return rs > 0;

    }
    
    
    public void deleteAllChiTietDichVu(String maHD,String maDV){
        JDBCHelper.executeUpdate(DELETE_ALL_CHITIETDICHVU, maHD,maDV);
    }

    public List<ChiTietDichVu> selectAllChiTietDichVu(String maHD, String maDV) {
        return selectChiTietDichVu(SELECT_ALL_CHITIETDICHVU, maHD, maDV, maDV);
    }

    public ChiTietDichVu selectChiTietDichVuCustom(String maHD, String maDV, String maDMC) {
        List<ChiTietDichVu> list = selectChiTietDichVu(SELECT_CHITIETDIVU_CUSTOM, maHD, maDV, maDV, maDMC);
        return list.size() > 0 ? list.get(0) : null;

    }

    public ChiTietDichVu selectChiTietDichVuNoCustom(String maHD, String maDV, String maDMC) {
        List<ChiTietDichVu> list = selectChiTietDichVu(SELECT_CHITIEDICHVU_NOCUSTOM, maHD, maDV, maDV, maDMC);
        return list.size() > 0 ? list.get(0) : null;

    }
    
    public int getSoLuongBan(String maCSVC,String ngayTc){
         try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(SELECT_SOLUONGBAN, maCSVC,ngayTc);
                while (rs.next()) {
                  return rs.getInt("SoLuongBan");
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
         return -1;
    }
    
    public int getSoLuongSuDung(String maCSVC,String ngayTc){
         try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(SELECT_SOLUONGSUDUNG, maCSVC,ngayTc);
                while (rs.next()) {
                  return rs.getInt("SoLuongSuDung");
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
         return -1;
    }


    private List selectDichVu(String sql, Object... args) {
        List<HopDongDichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HopDongDichVu model = readDichVuFromResultSet(rs);
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

    private List selectChiTietDichVu(String sql, Object... args) {
        List<ChiTietDichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ChiTietDichVu model = readChiTietDichVuFromResultSet(rs);
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

    private ChiTietDichVu readChiTietDichVuFromResultSet(ResultSet rs) throws SQLException {
        ChiTietDichVu ctdv = new ChiTietDichVu();

        ctdv.setMaHD(rs.getString("MaHD"));
        ctdv.setMaDV(rs.getString("MaDV"));
        ctdv.setMaCSVC(rs.getString("MaCSVC"));
        ctdv.setTenCSVC(rs.getString("TenCSVC"));
        ctdv.setChiPhi(rs.getLong("ChiPhi"));
        ctdv.setChiPhiPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        ctdv.setGhiChu(rs.getString("GhiChu"));
        return ctdv;

    }

    private HopDongDichVu readDichVuFromResultSet(ResultSet rs) throws SQLException {
        HopDongDichVu hdvc = new HopDongDichVu();

        hdvc.setMaHD(rs.getString("MaHD"));
        hdvc.setMaDV(rs.getString("MaDV"));
        hdvc.setMaGoi(rs.getString("MaGoi"));
        hdvc.setChiPhi(rs.getLong("ChiPhi"));
        hdvc.setChiPhiPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        hdvc.setGhiChu(rs.getString("GhiChu"));
        return hdvc;

    }
}
