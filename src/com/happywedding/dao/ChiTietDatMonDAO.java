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
            + "SET MaTD = ?, ChiPhi = ?,ChiPhiPhatSinh = ? ,GhiChu = ?\n"
            + "WHERE MaHD = ? AND MaTD = ?";
    private final String SELECT_DICHVUDATMON = "SELECT MaHD,MaTD,ChiPhi, \n"
            + "( SELECT SUM(ChiPhiPhatSinh * SoLuong)  FROM ChiTietDatMon WHERE MaHD = ? AND  MaTD = ? ) AS ChiPhiPhatSinh,\n"
            + "GhiChu FROM DichVuDatMon WHERE MaHD = ? AND MaTD = ?";

    private final String SELECT_THUCDONCHINH = "SELECT Top 1 MaHD,MaTD,ct.MaMA,TenMA,MaPL, ma.GiaTien AS ChiPhi,ct.ChiPhiPhatSinh,ThuTu,ct.SoLuong,GhiChu,HinhAnh FROM ChiTietDatMon ct\n"
            + "		INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA \n"
            + "		WHERE MaHD = ?   AND ma.MaPL != 'NUOC' ORDER BY  SoLuong DESC ";

    private final String SELECT_THUCDONPHU = "SELECT Top 1 MaHD,MaTD,ct.MaMA,TenMA,MaPL, ma.GiaTien AS ChiPhi,ct.ChiPhiPhatSinh,ThuTu,ct.SoLuong,GhiChu,HinhAnh FROM ChiTietDatMon ct\n"
            + "		INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA \n"
            + "		WHERE MaHD = ? AND SoLuong != -1  AND ma.MaPL != 'NUOC' ORDER BY SoLuong ASC ";

    private final String INSERT_CHITIETDATMON = "INSERT ChiTietDatMon (MaHD, MaMA,MaTD,ChiPhiPhatSinh ,ThuTu,SoLuong, GhiChu) VALUES (?, ?,?,?, ?,?, ?)";
    private final String SELECT_CHITIETDATMON = "SELECT MaHD,MaTD,ct.MaMA,TenMA,MaPL,ma.GiaTien AS ChiPhi,ct.ChiPhiPhatSinh,ThuTu,SoLuong,GhiChu,HinhAnh FROM ChiTietDatMon ct\n"
            + "	INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA \n"
            + "	WHERE MaHD = ? AND MaTD = ? ORDER BY ThuTu ASC";

    private final String DELETE_ALL_CHITIETDATMON = "DELETE ChiTietDatMon WHERE MaHD = ? AND MaTD = ?";
    private final String DELETE_DICHVUDATMON = "DELETE DichVuDatMon WHERE MaHD = ? AND MaTD = ?";

    private final String SELECT_MONAN_IN_THUCDON = "SELECT MaTD,ct.MaMA,TenMA,MaPL,ma.GiaTien,ThuTu,GhiChu,HinhAnh FROM ChiTietThucDon ct\n"
            + "INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA \n"
            + "WHERE MaTD = ?   ORDER BY ThuTu ASC ";

    private final String SELECT_MONAN_BY_MAPL = "SELECT MaHD,MaTD,ct.MaMA,TenMA,MaPL, ma.GiaTien AS ChiPhi,ct.ChiPhiPhatSinh,ThuTu,ct.SoLuong,GhiChu,HinhAnh FROM ChiTietDatMon ct\n"
            + "INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA \n"
            + "WHERE MaHD = ? AND MaPL = ?";
    
    private final String UPDATE_SOLUONG = "UPDATE ChiTietDatMon SET SoLuong = ? WHERE MaTD = ? AND MaMA = ?";
    
    private final String SELECT_MONAN_NOTIN_HOPDONG = "SELECT * FROM MonAn WHERE MaMA NOT IN( SELECT MaMA FROM ChiTietDatMon WHERE MaHD = ?  ) ";
    private final String SELECT_MONAN_NOTIN_THUCDON = "SELECT * FROM MonAn WHERE MaMA NOT IN( SELECT MaMA FROM ChiTietThucDon WHERE MaTD = ?  )";

    /*
    thêm vào bảng dịch vụ đặt món
     */
    public boolean insertDichVuDatMon(DichVuDatMon dvdm) {
        int rs = JDBCHelper.executeUpdate(INSERT_DICHVUDATMON, dvdm.getMaHD(), dvdm.getMaTD(), dvdm.getChiPhi(), dvdm.getGhiChu());
        return rs > 0;
    }

    public boolean updateDichVuDatMon(DichVuDatMon dvdm, String maTD) {
        int rs = JDBCHelper.executeUpdate(UPDATE_DICHVUDATMON, dvdm.getMaTD(), dvdm.getChiPhi(), dvdm.getChiPhiPhatSinh(),dvdm.getGhiChu(), dvdm.getMaHD(), dvdm.getMaTD());
        return rs > 0;
    }

    public DichVuDatMon selectDichVuDatMon(String maHD, String maTD) {
        List<DichVuDatMon> list = selectDichVuDatMon(SELECT_DICHVUDATMON, maHD, maTD, maHD, maTD);
        return list.size() > 0 ? list.get(0) : null;
    }

//    public List<ChiTietDatMon> selectDichVuDatMon() {
//        List<DichVuDatMon> list = selectDichVuDatMon(, args);
//        return null;
//    }
    public String selectThucDonChinh(String maHD) {
        List<String> list2 = new ArrayList<>();
        list2.add(maHD);
        List<DichVuDatMon> list = selectDichVuDatMon(SELECT_THUCDONCHINH, list2.toArray(new String[0]));
        return list.size() > 0 ? list.get(0).getMaTD() : null;
    }

    public String selectThucDonPhu(String maHD) {
        List<String> list2 = new ArrayList<>();
        list2.add(maHD);
        List<DichVuDatMon> list = selectDichVuDatMon(SELECT_THUCDONPHU, list2.toArray(new String[0]));
        return list.size() > 0 ? list.get(0).getMaTD() : null;
    }

    public List<ChiTietDatMon> selectChiTietDatMon(String maHD, String maTD) {
        return selectChiTietDatMon(SELECT_CHITIETDATMON, maHD, maTD);

    }

    public boolean insertChiTietDatMon(ChiTietDatMon ctdm) {
        int rs = JDBCHelper.executeUpdate(INSERT_CHITIETDATMON, ctdm.getMaHD(), ctdm.getMaMA(), ctdm.getMaTD(), ctdm.getChiPhiPhatSinh(), ctdm.getThuTu(), ctdm.getSoLuong(), ctdm.getGhiChu());
        return rs > 0;
    }

    /*
    reset lại các món ăn trong ChiTietDatMon
     */
    public boolean removeAllChiTietDatMon(String maHD, String maTD) {
        int rs = JDBCHelper.executeUpdate(DELETE_ALL_CHITIETDATMON, maHD, maTD);
        return rs > 0;
    }

    public boolean deleteDichVuDatMon(String maHD, String maTD) {
        int rs = JDBCHelper.executeUpdate(DELETE_DICHVUDATMON, maHD, maTD);
        return rs > 0;
    }

    /*
    lấy các món ăn có trong thực đơn
     */
    public List<ChiTietDatMon> selectByMaThucDon(String maTD) {
        return selectChiTietThucDon(SELECT_MONAN_IN_THUCDON, maTD);
    }
    
      /*
    lấy các món ăn có trong thực đơn
     */
    public List<ChiTietDatMon> selectByMaPhanLoai(String maHD,String maPL) {
        return selectChiTietDatMon(SELECT_MONAN_BY_MAPL, maHD,maPL);
    }
    
    public boolean updateChiTietDatMon(ChiTietDatMon ct){
        int rs = JDBCHelper.executeUpdate(UPDATE_SOLUONG,ct.getSoLuong(), ct.getMaTD(),ct.getMaMA());
        return rs > 0;
    }

    /*
    lấy các món ăn ko có trong hợp đồng
     */
    public List<MonAn> selectNotinHopDong(String maHD) {
        return selectMonAn(SELECT_MONAN_NOTIN_HOPDONG, maHD);
    }

    /*
    lấy các món ăn ko có trong thực đơn
     */
    public List<MonAn> selectNotinThucDon(String maTD) {
        return selectMonAn(SELECT_MONAN_NOTIN_THUCDON, maTD);
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

    private List selectChiTietThucDon(String sql, Object... args) {
        List<ChiTietDatMon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ChiTietDatMon model = readChiTietThucDonFromResultSet(rs);
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

    private List selectMonAn(String sql, Object... args) {
        List<MonAn> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    MonAn model = readMonAnFromResultSet(rs);
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
        ctdm.setMaTD(rs.getString("MaTD"));
        ctdm.setMaMA(rs.getString("MaMA"));
        ctdm.setTenMA(rs.getString("TenMA"));
        ctdm.setMaPL(rs.getString("MaPL"));
        ctdm.setGia(rs.getLong("ChiPhi"));
        ctdm.setChiPhiPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        ctdm.setThuTu(rs.getInt("ThuTu"));
        ctdm.setSoLuong(rs.getInt("SoLuong"));

        ctdm.setGhiChu(rs.getString("GhiChu"));
        return ctdm;
    }

    private ChiTietDatMon readChiTietThucDonFromResultSet(ResultSet rs) throws SQLException {
        ChiTietDatMon ctdm = new ChiTietDatMon();

        ctdm.setMaMA(rs.getString("MaMA"));
        ctdm.setTenMA(rs.getString("TenMA"));
        ctdm.setMaPL(rs.getString("MaPL"));
        ctdm.setGia(rs.getLong("GiaTien"));
        ctdm.setThuTu(rs.getInt("ThuTu"));
        //ctdm.setSoLuong(rs.getInt("SoLuong"));
        ctdm.setGhiChu(rs.getString("GhiChu"));
        return ctdm;
    }

    private DichVuDatMon readDichVuDatMonFromResultSet(ResultSet rs) throws SQLException {
        DichVuDatMon dvdm = new DichVuDatMon();

        dvdm.setMaHD(rs.getString("MaHD"));
        dvdm.setMaTD(rs.getString("MaTD"));
        dvdm.setChiPhi(rs.getLong("ChiPhi"));
        dvdm.setChiPhiPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        dvdm.setGhiChu(rs.getString("GhiCHu"));
        return dvdm;

    }

    private MonAn readMonAnFromResultSet(ResultSet rs) throws SQLException {
        MonAn ma = new MonAn();

        ma.setMaMA(rs.getString("MaMA"));
        ma.setTenMA(rs.getString("TenMA"));
        ma.setGiaTien(rs.getLong("GiaTien"));
        ma.setMaPL(rs.getString("MaPL"));
        ma.setHinhAnh(rs.getString("HinhAnh"));
        return ma;

    }

}
