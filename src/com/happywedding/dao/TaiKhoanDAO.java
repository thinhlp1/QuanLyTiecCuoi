/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.HopDong;
import com.happywedding.model.NhanVien;
import com.happywedding.model.TaiKhoan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanDAO extends AbstractDAO<TaiKhoan> {

    private final String INSERT = "INSERT INTO dbo.TaiKhoan\n"
            + "(\n"
            + "    MaNhanVien,\n"
            + "    TenDangNhap,\n"
            + "    MatKhau,\n"
            + "    VaiTro\n"
            + ")\n"
            + "VALUES\n"
            + "(?,?,?,?)";
    private final String UPDATE = "UPDATE dbo.TaiKhoan SET MatKhau = ? WHERE MaTaiKhoan = ?";
    private final String CHANGE_PASS = "UPDATE dbo.TaiKhoan SET MatKhau = ? WHERE TenDangNhap = ?";
    private final String SUA = "UPDATE dbo.TaiKhoan SET TenDangNhap = ?, MatKhau = ?, VaiTro = ? WHERE MaTaiKhoan = ?";
    private final String SECLECT_ALL = "SELECT * FROM dbo.TaiKhoan";
    private final String SECLECT_BYID = "SELECT * FROM dbo.TaiKhoan WHERE MaTaiKhoan = ?";
    private final String SECLECT_TENDANGNHAP = "SELECT MaTaiKhoan, MaNhanVien, TenDangNhap, MatKhau, VaiTro FROM dbo.TaiKhoan where TenDangNhap=?";
    private final String DELETE = "DELETE FROM dbo.TaiKhoan where TenDangNhap=?";

    @Override
    public boolean insert(TaiKhoan entity) {
        int rs = JDBCHelper.executeUpdate(INSERT, entity.getMaNhanVien(), entity.getMaTaiKhoan(), entity.getMatKhau(), entity.getVaiTro());
        return rs > 0;
    }

    @Override
    public boolean update(TaiKhoan entity) {
        int rs = JDBCHelper.executeUpdate(CHANGE_PASS, entity.getMatKhau(), entity.getTenDangNhap());
        return rs > 0;
    }
    
    public boolean sua(TaiKhoan entity) {
        int rs = JDBCHelper.executeUpdate(SUA, entity.getTenDangNhap(), entity.getMatKhau(), entity.getVaiTro(), entity.getMaTaiKhoan());
        return rs > 0;
    }

    @Override
    public void delete(String id) {
        int rs = JDBCHelper.executeUpdate(DELETE, id);
    }

    //@Override
    //public List<TaiKhoan> select() {
    //    return select(SECLECT_ALL);
    //}
    
    public List<TaiKhoan> selectTenDangNhap(String tenDangNhap){   
        return select(SECLECT_TENDANGNHAP, tenDangNhap);
    }

    @Override
    public TaiKhoan findById(String id) {
        List<TaiKhoan> list = select(SECLECT_BYID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List select(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    TaiKhoan taiKhoan = readFromResultSet(rs);
                    list.add(taiKhoan);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    

    private TaiKhoan readFromResultSet(ResultSet rs) throws SQLException {
        TaiKhoan TaiKhoan = new TaiKhoan();
        TaiKhoan.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
        TaiKhoan.setMaNhanVien(rs.getString("MaNhanVien"));
        TaiKhoan.setTenDangNhap(rs.getString("TenDangNhap"));
        TaiKhoan.setMatKhau(rs.getString("MatKhau"));
        TaiKhoan.setVaiTro(rs.getString("VaiTro"));
        return TaiKhoan;
    }

    public boolean kiemTraTaiKhoan(String username, String pass) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaiKhoan> select() {
        return select(SECLECT_ALL);
    }

}
