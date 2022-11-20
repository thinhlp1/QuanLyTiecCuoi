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

    private final String INSERT_CHITIETDATMON = "INSERT ChiTietDatMon (MaHD, MaMA,ChiPhiPhatSinh ,ThuTu, GhiChu) VALUES (?, ?,?, ?, ?)";
    private final String SELECT_CHITIETDATMON = "SELECT MaHD,ct.MaMA,TenMA,MaPL,ma.GiaTien AS ChiPhi,ct.ChiPhiPhatSinh,ThuTu,GhiChu,HinhAnh FROM ChiTietDatMon ct\n" +
"	INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA \n" +
"	WHERE MaHD = ?";

    private final String DELETE_ALL_CHITIETDATMON = "DELETE ChiTietDatMon WHERE MaHD = ?";

    private final String SELECT_MONAN_IN_THUCDON = "SELECT MaTD,ct.MaMA,TenMA,MaPL,ma.GiaTien,ThuTu,GhiChu,HinhAnh FROM ChiTietThucDon ct\n"
            + "INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA \n"
            + "WHERE MaTD = ?";
    
    private final String SELECT_MONAN_NOTIN_HOPDONG = "SELECT * FROM MonAn WHERE MaMA NOT IN( SELECT MaMA FROM ChiTietDatMon WHERE MaHD = ?  )";
    private final String SELECT_MONAN_NOTIN_THUCDON = "SELECT * FROM MonAn WHERE MaMA NOT IN( SELECT MaMA FROM ChiTietThucDon WHERE MaTD = ?  )";
    
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
        List<DichVuDatMon> list = selectDichVuDatMon(SELECT_DICHVUDATMON, maHD,maHD);
        return list.size() > 0 ? list.get(0) : null;
    }


    public List<ChiTietDatMon> selectChiTietDatMon(String maHD) {
        return selectChiTietDatMon(SELECT_CHITIETDATMON, maHD);


    }

    public boolean insertChiTietDatMon(ChiTietDatMon ctdm) {
        int rs = JDBCHelper.executeUpdate(INSERT_CHITIETDATMON, ctdm.getMaHD(), ctdm.getMaMA(),ctdm.getThuTu() ,ctdm.getChiPhiPhatSinh(), ctdm.getGhiChu());
        return rs > 0;
    }

    /*
    reset lại các món ăn trong ChiTietDatMon
     */
    public boolean removeAllChiTietDatMon(String maHD) {
        int rs = JDBCHelper.executeUpdate(DELETE_ALL_CHITIETDATMON, maHD);
        return rs > 0;
    }

    /*
    lấy các món ăn có trong thực đơn
     */
    public List<MonAn> selectByMaThucDon(String maTD) {
       return selectMonAn(SELECT_MONAN_IN_THUCDON, maTD);
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
        ctdm.setMaMA(rs.getString("MaMA"));
        ctdm.setTenMA(rs.getString("TenMA"));
        ctdm.setGia(rs.getLong("ChiPhi"));
        ctdm.setChiPhiPhatSinh(rs.getLong("ChiPhiPhatSinh"));
        ctdm.setThuTu(rs.getInt("ThuTu"));
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
