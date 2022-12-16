/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.CoSoVatChat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CoSoVatChatDAO extends AbstractDAO<CoSoVatChat> {

    private final String INSERT_CSVC = "INSERT INTO CoSoVatChat(MaCSVC,TenCSVC,MaDMC,SoLuong,GiaThue,GhiChu)VALUES(?,?,?,?,?,?)";
    private final String UPDATE_CSVC = "UPDATE CoSoVatChat SET TenCSVC = ?,MaDMC = ?,SoLuong = ?,GiaThue = ?,GhiChu = ? WHERE MaCSVC = ?";
    private final String DELELTE_CSVC = "DELETE FROM CoSoVatChat WHERE MaCSVC = ?";
    private final String SELECT_ALL = "SELECT csvc.MaCSVC,csvc.TenCSVC,dmc.MaDMC,dmc.TenDM,csvc.SoLuong,csvc.GiaThue,csvc.GhiChu,dm.MaDM,dm.TenDM FROM CoSoVatChat csvc INNER JOIN DanhMucCon dmc ON csvc.MaDMC = dmc.MaDMC INNER JOIN DanhMuc dm ON dm.MaDM = dmc.MaDM";
    private final String SELECT_ALL_CSVC = "SELECT MaCSVC,TenCSVC,csvc.MaDMC,SoLuong,GiaThue,GhiChu,dm.MaDM FROM CoSoVatChat csvc INNER JOIN DanhMucCon dmc ON csvc.MaDMC = dmc.MaDMC \n"
            + "INNER JOIN DanhMuc dm ON dm.MaDM = dmc.MaDM";
    private final String SELECT_BY_ID = "SELECT MaCSVC,TenCSVC,csvc.MaDMC,SoLuong,GiaThue,GhiChu,dm.MaDM FROM CoSoVatChat csvc INNER JOIN DanhMucCon dmc ON csvc.MaDMC = dmc.MaDMC \n"
            + "INNER JOIN DanhMuc dm ON dm.MaDM = dmc.MaDM WHERE MaCSVC = ?";
    private final String SELECT_BY_MADMC = "SELECT MaCSVC,TenCSVC,csvc.MaDMC,SoLuong,GiaThue,GhiChu,dm.MaDM FROM CoSoVatChat csvc INNER JOIN DanhMucCon dmc ON csvc.MaDMC = dmc.MaDMC \n"
            + "INNER JOIN DanhMuc dm ON dm.MaDM = dmc.MaDM WHERE dmc.MaDMC = ?";
    private final String SELECT_BY_MADM = "SELECT MaCSVC,TenCSVC,csvc.MaDMC,SoLuong,GiaThue,GhiChu,dm.MaDM FROM CoSoVatChat csvc INNER JOIN DanhMucCon dmc ON csvc.MaDMC = dmc.MaDMC \n"
            + "INNER JOIN DanhMuc dm ON dm.MaDM = dmc.MaDM WHERE dm.MaDM = ?";

    private final String SELECT_SLSD_AOGHE = "  SELECT SUM(hd.SoLuongBan) * 10 AS SoLuongSuDung FROM CoSoVatChat cs\n"
            + "  INNER JOIN ChiTietDichVu ct ON cs.MaCSVC = ct.MaCSVC \n"
            + "  INNER JOIN HopDong hd ON ct.MaHD = hd.MaHD\n"
            + "  WHERE cs.MaCSVC = ? AND NgayToChuc = ? AND hd.MaHD != ?  AND hd.TrangThai != 'XOA'";

    private final String SELECT_SLSD_TRAIBAN = "  SELECT SUM(hd.SoLuongBan)  AS SoLuongSuDung FROM CoSoVatChat cs\n"
            + "  INNER JOIN ChiTietDichVu ct ON cs.MaCSVC = ct.MaCSVC \n"
            + "  INNER JOIN HopDong hd ON ct.MaHD = hd.MaHD\n"
            + "  WHERE cs.MaCSVC = ? AND NgayToChuc = ? AND hd.MaHD != ?  AND hd.TrangThai != 'XOA'";

    private final String SELECT_SLSD_KHAC = "  SELECT COUNT(*) AS SoLuongSuDung FROM CoSoVatChat cs\n"
            + "  INNER JOIN ChiTietDichVu ct ON cs.MaCSVC = ct.MaCSVC \n"
            + "  INNER JOIN HopDong hd ON ct.MaHD = hd.MaHD\n"
            + "  WHERE cs.MaCSVC = ? AND NgayToChuc = ? AND hd.MaHD != ? AND hd.TrangThai != 'XOA'";

    @Override
    public boolean insert(CoSoVatChat entity) {
        int rs = JDBCHelper.executeUpdate(INSERT_CSVC,
                entity.getMaCSVC(),
                entity.getTenCSVC(),
                entity.getMaDanhMucCon(),
                entity.getSoLuong(),
                entity.getGiaThue(),
                entity.getGhiChu());
        return rs > 0;
    }

    @Override
    public boolean update(CoSoVatChat entity) {
        int rs = JDBCHelper.executeUpdate(UPDATE_CSVC,
                entity.getTenCSVC(),
                entity.getMaDanhMucCon(),
                entity.getSoLuong(),
                entity.getGiaThue(),
                entity.getGhiChu(),
                entity.getMaCSVC()
        );
        return rs > 0;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<CoSoVatChat> select() {
        return select(SELECT_ALL);
    }

    @Override
    public CoSoVatChat findById(String id) {
        List<CoSoVatChat> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List select(String sql, Object... args) {
        List<CoSoVatChat> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    CoSoVatChat model = readFromResultSet(rs);
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

    private CoSoVatChat readFromResultSet(ResultSet rs) throws SQLException {
        //MaCSVC,TenCSVC,MaDMC,SoLuong,GhiChu
        CoSoVatChat model = new CoSoVatChat();
        model.setMaCSVC(rs.getString("MaCSVC"));
        model.setTenCSVC(rs.getString("TenCSVC"));

        model.setMaDanhMuc(rs.getString("MaDM"));
        //    model.setTenDanhMuc(rs.getString("TenDM"));
        model.setMaDanhMucCon(rs.getString("MaDMC"));
        // model.setMaDanhMucCon(rs.getString("MaDMC"));
        model.setGiaThue(rs.getLong("GiaThue"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setGhiChu(rs.getString("GhiChu"));
        return model;
    }

    public int selectSLSDAoGhe(String maCSVC, Date ngayToChuc, String maHD) {
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(SELECT_SLSD_AOGHE, maCSVC, ngayToChuc,maHD);
                while (rs.next()) {
                    Integer slsd = rs.getInt("SoLuongSuDung");
                    if (slsd == null) {
                        return 0;
                    }
                    return slsd;
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }
    
       public int selectSLSDTraiBan(String maCSVC, Date ngayToChuc,String maHD) {
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(SELECT_SLSD_TRAIBAN, maCSVC, ngayToChuc,maHD);
                while (rs.next()) {
                    Integer slsd = rs.getInt("SoLuongSuDung");
                    if (slsd == null) {
                        return 0;
                    }
                    return slsd;
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }

       
       
          public int selectSLSDKhac(String maCSVC, Date ngayToChuc,String maHD) {
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(SELECT_SLSD_KHAC, maCSVC, ngayToChuc,maHD);
                while (rs.next()) {
                    Integer slsd = rs.getInt("SoLuongSuDung");
                    if (slsd == null) {
                        return 0;
                    }
                    return slsd;
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }


    /*
    lấy danh sách csvc theo mã danh mục
     */
    public List<CoSoVatChat> selectByMaDM(String maDM) {
        return select(SELECT_BY_MADM, maDM);
    }

    /*
    lấy danh sách csvc theo mã danh mục con
     */
    public List<CoSoVatChat> selectByMaDMC(String maDMC) {
        return select(SELECT_BY_MADMC, maDMC);
    }
}
