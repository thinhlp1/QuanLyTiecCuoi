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
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HopDongDAO extends AbstractDAO<HopDong> {

    private final String INSERT = "INSERT INTO dbo.HopDong ( MaHD,MaNL,SoLuongBan,Sanh,NgayLap,NgayDuyet,MaND,NgayToChuc,ThoiGianBatDau,ThoiGianKetThuc,TrangThai,The,TienCoc,TongTien) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String DELETE = "DELETE dbo.HopDong WHERE MaHD = ?";
    private final String UPDATE = "UPDATE dbo.HopDong SET MaNL = ?,SoLuongBan = ?,Sanh = ?, NgayLap = ?, NgayDuyet = ?, MaND = ?, NgayToChuc = ?,ThoiGianBatDau = ?, ThoiGianKetThuc = ?, TrangThai = ?, The = ?, TienCoc = ?, TongTien = ? where MaHD = ? ";
    private final String SECLECT_ALL = "SELECT hd.MaHD,hd.MaNL,hd.MaND,nv.HoTen AS NguoiLap,SoLuongBan,s.TenSanh,hd.NgayLap,hd.NgayDuyet,kh.MaKH,kh.HoTen,kh.SoDienThoai,hd.NgayToChuc,hd.ThoiGianBatDau,hd.ThoiGianKetThuc,tt.MaTT,tt.TenTT \n"
            + ",hd.The,hd.TienCoc,hd.TongTien,\n"
            + "( SELECT HoTen FROM NhanVien WHERE MaNV = hd.MaND ) AS NguoiDuyet\n"
            + "FROM HopDong hd INNER JOIN KhachHang kh ON hd.MaHD = kh.MaHD\n"
            + "INNER JOIN TrangThaiHopDong tt ON hd.TrangThai = tt.MaTT\n"
            + "INNER JOIN NhanVien nv ON nv.MaNV = hd.MaNL\n"
            + "INNER JOIN Sanh s ON s.MaSanh = hd.Sanh";
    private final String SECLECT_BY_ID = "SELECT hd.MaHD,hd.MaNL,hd.MaND,nv.HoTen AS NguoiLap,SoLuongBan,s.TenSanh,hd.NgayLap,hd.NgayDuyet,kh.MaKH,kh.HoTen,kh.SoDienThoai,hd.NgayToChuc,hd.ThoiGianBatDau,hd.ThoiGianKetThuc,tt.MaTT,tt.TenTT \n"
            + ",hd.The,hd.TienCoc,hd.TongTien,\n"
            + "( SELECT HoTen FROM NhanVien WHERE MaNV = hd.MaND ) AS NguoiDuyet\n"
            + "FROM HopDong hd INNER JOIN KhachHang kh ON hd.MaHD = kh.MaHD\n"
            + "INNER JOIN TrangThaiHopDong tt ON hd.TrangThai = tt.MaTT\n"
            + "INNER JOIN NhanVien nv ON nv.MaNV = hd.MaNL\n"
            + "INNER JOIN Sanh s ON s.MaSanh = hd.Sanh\n"
            + "WHERE hd.MaHD = ?";
    private final String UPDATE_TRANGTHAI = "UPDATE dbo.HopDong SET TrangThai = ? WHERE MaHD = ?";
    private final String UPDATE_CHIPHI = "UPDATE HopDong SET TienCoc = ? , TongTien = ? WHERE MaHD = ?";
    private final String UPDATE_SANH = "UPDATE HopDong SET Sanh = ? WHERE MaHD = ?";
    private final String TINH_TIEN = "EXEC tinhTien @MaHD = ?";
    private final String TINH_TIEN_VOI_SANH = "EXEC tinhTienVoiSanh @MaHD = ? , @MaSanh = ?,@SoLuongBan = ?";
    private final String CHECK_SANH = "SELECT * FROM HopDong hd INNER JOIN Sanh s  ON hd.Sanh = s.MaSanh\n"
            + "WHERE MaSanh = ? AND NgayToChuc = ? AND (  ( CAST(? AS Time)   BETWEEN ThoiGianBatDau AND ThoiGianKetThuc )"
            + "OR  (  ( CAST(? AS Time)   BETWEEN ThoiGianBatDau AND ThoiGianKetThuc )   )   ) AND MaHD != ?";

    @Override
    public boolean insert(HopDong entity) {
        int rs = JDBCHelper.executeUpdate(INSERT, entity.getMaHD(), entity.getMaNL(), entity.getSoLuongBan(), entity.getSanh(), entity.getNgayLap(), entity.getNgayDuyet(), entity.getMaND(), entity.getNgayToChuc(), entity.getThoiGianBatDau(), entity.getThoiGianKetThuc(), entity.getTrangThai(), entity.getThue(), entity.getTienCoc(), entity.getTongTien());
        return rs > 0;
    }

    @Override
    public boolean update(HopDong entity) {
        int rs = JDBCHelper.executeUpdate(UPDATE, entity.getMaNL(), entity.getSoLuongBan(), entity.getSanh(), entity.getNgayLap(), entity.getNgayDuyet(), entity.getMaND(), entity.getNgayToChuc(), entity.getThoiGianBatDau(), entity.getThoiGianKetThuc(), entity.getTrangThai(), entity.getThue(), entity.getTienCoc(), entity.getTongTien(), entity.getMaHD());
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

    @Override
    public HopDong findById(String id) {
        List<HopDong> list = select(SECLECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public void danhDauXoa(String maHD, String MaTT) {
        int rs = JDBCHelper.executeUpdate(UPDATE_TRANGTHAI, MaTT);
    }

    public boolean updateTrangThai(String maHD, String MaTT) {
        int rs = JDBCHelper.executeUpdate(UPDATE_TRANGTHAI, MaTT, maHD);
        return rs > 0;
    }

    public boolean updateSanh(String maSanh, String maHD) {
        int rs = JDBCHelper.executeUpdate(UPDATE_SANH, maSanh, maHD);
        return rs > 0;
    }

    public HopDong checkSanh(String maSanh, Date ngayToChuc, String batDau, String ketThuc, String maHD) {
        try {
            ResultSet rs = null;
            HopDong hopDong = new HopDong();
            try {
                rs = JDBCHelper.executeQuery(CHECK_SANH, maSanh, ngayToChuc, batDau, ketThuc, maHD);
                while (rs.next()) {
                    hopDong.setMaHD(rs.getString("MaHD"));
                    hopDong.setNgayLap(rs.getDate("NgayLap"));
                    
                  //  hopDong.setSdtKhachHang(rs.getString("SoDienThoai"));
                    hopDong.setNgayToChuc(rs.getDate("NgayToChuc"));
                    hopDong.setThoiGianBatDau(rs.getString("ThoiGianBatDau"));
                    hopDong.setThoiGianKetThuc(rs.getString("ThoiGianKetThuc"));
                    return hopDong;
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    // trả về chi phí và chi phí phát sinh trong toàn bộ hợp đồng
    public List<Long> tinhToan(String maHD) {
        List<Long> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(TINH_TIEN, maHD);
                while (rs.next()) {
                    long chiPhi = rs.getLong("ChiPhi");
                    long chiPhiPhatSinh = rs.getLong("ChiPhiPhatSinh");
                    list.add(chiPhi);
                    list.add(chiPhiPhatSinh);
                    return list;
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    // trả về chi phí và chi phí phát sinh trong toàn bộ hợp đồng
    public List<Long> tinhToan(String maHD, String maSanh, int soLuongBan) {
        List<Long> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(TINH_TIEN_VOI_SANH, maHD, maSanh, soLuongBan);
                while (rs.next()) {
                    long chiPhi = rs.getLong("ChiPhi");
                    long chiPhiPhatSinh = rs.getLong("ChiPhiPhatSinh");
                    list.add(chiPhi);
                    list.add(chiPhiPhatSinh);
                    return list;
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public void updateChiPhi(long tienCoc, long tongTien, String maHD) {
        JDBCHelper.executeUpdate(UPDATE_CHIPHI, tienCoc, tongTien, maHD);
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

    private HopDong readFromResultSet(ResultSet rs) throws SQLException {
        HopDong hopDong = new HopDong();

        hopDong.setMaHD(rs.getString("MaHD"));
        hopDong.setMaNL(rs.getString("MaNL"));
        hopDong.setMaND(rs.getString("MaND"));
        hopDong.setTenNguoiLap(rs.getString("NguoiLap"));
        hopDong.setTenNguoiDuyet(rs.getString("NguoiDuyet"));
        hopDong.setSoLuongBan(rs.getLong("SoLuongBan"));
        hopDong.setSanh(rs.getString("TenSanh"));
        hopDong.setNgayLap(rs.getDate("NgayLap"));
        hopDong.setNgayDuyet(rs.getDate("NgayDuyet"));
        hopDong.setMaKH(rs.getInt("MaKH"));
        hopDong.setTenKhachHang(rs.getString("HoTen"));
        hopDong.setSdtKhachHang(rs.getString("SoDienThoai"));
        hopDong.setNgayToChuc(rs.getDate("NgayToChuc"));
        hopDong.setThoiGianBatDau(rs.getString("ThoiGianBatDau"));
        hopDong.setThoiGianKetThuc(rs.getString("ThoiGianKetThuc"));
        hopDong.setTrangThai(rs.getString("MaTT"));
        hopDong.setTenTrangThai(rs.getString("TenTT"));
        hopDong.setThue(rs.getLong("The"));
        hopDong.setTienCoc(rs.getLong("TienCoc"));
        hopDong.setTongTien(rs.getLong("TongTien"));
        return hopDong;
    }
}
