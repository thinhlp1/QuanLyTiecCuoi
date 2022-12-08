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
import com.happywedding.model.PhanCongModel;
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
    private final String SECLECT_BYID_PHANCONG = "SELECT * FROM dbo.PhanCong WHERE MaHD = ?";

    private final String INSERT_CHITIETPHANCONG = "INSERT INTO dbo.ChiTietPhanCong(MaPC,MaNV,NgayPC,GioBatDau,GioKetThuc)VALUES(?,?,?,?,?)";
    private final String DELETE_CHITIETPHANCONG = "DELETE dbo.ChiTietPhanCong WHERE MaPC = ? AND MaNV = ?";
    private final String UPDATE_CHITIETPHANCONG = "UPDATE dbo.ChiTietPhanCong SET  NgayPC = ?, GioBatDau = ?, GioKetThuc = ? WHERE MaPC = ? AND MaNV = ?";
    private final String SECLECT_ALL_CHITIETPHANCONG = "	SELECT pc.MaPC,hd.MaHD,nv.MaNV,nv.HoTen,TenVT ,NgayPC,GioBatDau,GioKetThuc FROM dbo.ChiTietPhanCong ct\n"
            + "	INNER JOIN PhanCong pc ON pc.MaPC = ct.MaPC\n"
            + "	INNER JOIN HopDong hd ON hd.MaHD = pc.MaHD\n"
            + "	INNER JOIN NhanVien nv ON nv.MaNV = ct.MaNV\n"
            + "	INNER JOIN VaiTro vt ON vt.MaVT = nv.MaVT\n"
            + "	WHERE hd.MaHD = ?";
    private final String SECLECT_BYID_CHITIETPHANCONG = "	SELECT pc.MaPC,hd.MaHD,nv.MaNV,nv.HoTen,TenVT ,NgayPC,GioBatDau,GioKetThuc FROM dbo.ChiTietPhanCong ct\n"
            + "	INNER JOIN PhanCong pc ON pc.MaPC = ct.MaPC\n"
            + "	INNER JOIN HopDong hd ON hd.MaHD = pc.MaHD\n"
            + "	INNER JOIN NhanVien nv ON nv.MaNV = ct.MaNV\n"
            + "	INNER JOIN VaiTro vt ON vt.MaVT = nv.MaVT\n"
            + "	WHERE hd.MaHD = ?";

    private final String CHECK_PHANCONG = "SELECT * FROM dbo.PhanCong WHERE MaHD = ? AND MaNguoiPC =?";
    private final String CHECK_PHANCONG1 = "SELECT * FROM dbo.PhanCong WHERE MaHD = ?";

    private final String SELECT_NHANVIEN_POSSIBLE = "SELECT  nv.MaNV,nv.HoTen,nv.NgaySinh,nv.GioiTinh,nv.SoDienThoai,nv.CCCD_CMND,nv.Email,nv.HinhAnh,nv.MaPB,pb.TenPB,nv.MaVT,vt.TenVT,TrangThai FROM  NhanVien nv\n"
            + "INNER JOIN PhongBan pb ON nv.MaPB = pb.MaPB INNER JOIN VaiTro vt ON nv.MaVT = vt.MaVT\n"
            + "WHERE MaNV NOT IN (  SELECT MaNV FROM PhanCong pc INNER JOIN ChiTietPhanCong ct ON ct.MaPC = pc.MaPC WHERE pc.MaHD = ?   ) ";

    public boolean insertPhanCong(PhanCongModel pc) {
        int rs = JDBCHelper.executeUpdate(INSERT_PHANCONG, pc.getMaHD(), pc.getMaNguoiPC());
        return rs > 0;
    }

    public boolean updatePhanCong(PhanCongModel entity) {
        int rs = JDBCHelper.executeUpdate(UPDATE_PHANCONG, entity.getMaHD(), entity.getMaNguoiPC());
        return rs > 0;
    }

    public void deletePhanCong(String id) {
        int rs = JDBCHelper.executeUpdate(DELETE_PHANCONG, id);
    }

    public List<HopDong> selectAllPhanCong() {

        return selectPhanCong(SECLECT_ALL_PHANCONG);

    }

    public List<NhanVien> selectNhanVienPossible(String maHD) {
        return select2(SELECT_NHANVIEN_POSSIBLE, maHD);
    }

    public PhanCongModel findById(String id) {
        List<PhanCongModel> list = selectPhanCong(SECLECT_BYID_PHANCONG, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public PhanCongModel checkPhanCong(String maHD, String maNV) {
        List<PhanCongModel> list = selectPhanCong(CHECK_PHANCONG, maHD, maNV);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List selectPhanCong(String sql, Object... args) {
        List<PhanCongModel> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    PhanCongModel phanCong = readFromResultSetPhanCong(rs);
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

    private PhanCongModel readFromResultSetPhanCong(ResultSet rs) throws SQLException {
        PhanCongModel phanCong = new PhanCongModel();
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
        int rs = JDBCHelper.executeUpdate(UPDATE_CHITIETPHANCONG, entity.getNgayPhanCong(), 
                entity.getThoiGianBatDau(), entity.getThoiGianKetThuc(),entity.getMaPC(), entity.getMaNV());
        return rs > 0;
    }

    public void deleteChiTietPhanCong(String maPC, String maNV) {
        int rs = JDBCHelper.executeUpdate(DELETE_CHITIETPHANCONG, maPC, maNV);
    }

    public List<ChiTietPhanCong> selectAllChiTietPhanCong(String maHD) {
        return selectChiTietPhanCong(SECLECT_ALL_CHITIETPHANCONG, maHD);
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
        ChiTietPhanCong.setTenNV(rs.getString("HoTen"));
        ChiTietPhanCong.setTenVT(rs.getString("TenVT"));
        ChiTietPhanCong.setNgayPhanCong(rs.getDate("NgayPC"));
        ChiTietPhanCong.setThoiGianBatDau(rs.getString("GioBatDau"));
        ChiTietPhanCong.setThoiGianKetThuc(rs.getString("GioKetThuc"));
        return ChiTietPhanCong;
    }

    private List select2(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien model = readFromResultSet2(rs);

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

    private NhanVien readFromResultSet2(ResultSet rs) throws SQLException {
        //MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getString("MaNV"));
        model.setHoTen(rs.getString("HoTen"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setSoDienThoai(rs.getString("SoDienThoai"));
        model.setEmail(rs.getString("Email"));
        model.setCMND_CCCD(rs.getString("CCCD_CMND"));
        model.setHinhAnh(rs.getString("HinhAnh"));
        model.setMaPB(rs.getString("MaPB"));
        model.setMaVT(rs.getString("MaVT"));
        model.setTenVT(rs.getString("TenVT"));
        model.setTenPB(rs.getString("TenPB"));
        model.setTrangThai(rs.getByte("TrangThai"));
        return model;
    }

}
