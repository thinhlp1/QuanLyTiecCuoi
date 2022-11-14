/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.CoSoVatChat;
import com.happywedding.model.Sanh;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SanhDAO extends AbstractDAO<Sanh>{
    
    private final String INSERT_SANH = "INSERT INTO Sanh (MaSanh, , ThoiLuong, NgayKG, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?)";

    private final String UPDATE_SANH = "UPDATE Sanh SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=? WHERE\n"
            + "MaKH=?";
    private final String DELELTE_SANH = "DELETE FROM Sanh WHERE MaKH=?";
    private final String SELECT_ALL = "SELECT * FROM Sanh";
    private final String SELECT_BY_ID = "SELECT * FROM Sanh WHERE MaKH=?";
   

    public boolean insert(Sanh sanh) {
        int rs = JDBCHelper.executeUpdate(INSERT_SANH,
                sanh.getMaSanh(),
                sanh.getTenSanh(),
                sanh.getMaPL(),
                sanh.getSucChua(),
                sanh.getGiaThueSanh(),
                sanh.getGiaBan());
        return rs > 0;
    }

    public boolean update(Sanh sanh) {
        int rs = JDBCHelper.executeUpdate(UPDATE_SANH,
                sanh.getMaSanh(),
                sanh.getTenSanh(),
                sanh.getMaPL(),
                sanh.getSucChua(),
                sanh.getGiaThueSanh(),
                sanh.getGiaBan());
        return rs > 0;
    }

    public void delete(String id) {
        int rs = JDBCHelper.executeUpdate(DELELTE_SANH, id);
    }

    @Override
    public List select() {
        return select(SELECT_ALL);
    }

    @Override
    public Sanh findById(String id) {
        List<Sanh> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List select(String sql, Object... args) {
        List<Sanh> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    //Sanh sanh = readFromResultSet(rs);
                 //   list.add(sanh);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    
    // sửa lại
//    private Sanh readFromResultSet(ResultSet rs) throws SQLException {
//        Sanh sanh = new Sanh();
//
//        sanh.setMaSanh(rs.getInt("MaKH"));
//        sanh.setFee(rs.getDouble("HocPhi"));
//        sanh.setDuration(rs.getInt("ThoiLuong"));
//        sanh.setOpeningDay(rs.getDate("NgayKG"));
//        sanh.setNote(rs.getString("GhiChu"));
//       
//        sanh.setMaNV(rs.getString("MaNV"));
//        sanh.setCreateDay(rs.getDate("NgayTao"));
//        sanh.setMaCD(rs.getString("MaCD"));
//
//        return sanh;
//
//    }
    
//    public List<Sanh> select() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public Sanh findById(String id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
}
