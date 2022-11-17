/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.MonAn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class MonAnDAO extends AbstractDAO<MonAn>{

    private final String INSERT_SANH = "INSERT INTO MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL, TenPL) VALUES (?, ?, ?, ?, ?, ?)";

    private final String UPDATE_SANH = "UPDATE MonAn SET MaMA=?, TenMA=?, HinhAnh=?, GiaTien=?, MaPL=?, TenPL=? WHERE\n"
            + "MaMA=?";
    private final String DELELTE_SANH = "DELETE FROM MonAn WHERE MaMA=?";
    private final String SELECT_ALL = "SELECT * FROM MonAn";
    private final String SELECT_BY_ID = "SELECT * FROM MonAn WHERE MaMA=?";
   

    public boolean insert(MonAn monAn) {
        int rs = JDBCHelper.executeUpdate(INSERT_SANH,
                monAn.getMaMA(),
                monAn.getTenMA(),
                monAn.getHinhAnh(),
                monAn.getGiaTien(),
                monAn.getMaPL(),
                monAn.getTenPL()
        );
        return rs > 0;
    }

    public boolean update(MonAn monAn) {
        int rs = JDBCHelper.executeUpdate(UPDATE_SANH,
                monAn.getMaMA(),
                monAn.getTenMA(),
                monAn.getHinhAnh(),
                monAn.getGiaTien(),
                monAn.getMaPL(),
                monAn.getTenPL()
        );
        return rs > 0;
    }

    public void delete(String id) {
        int rs = JDBCHelper.executeUpdate(DELELTE_SANH, id);
    }

    @Override
    public List<MonAn> select() {
        return select(SELECT_ALL);
    }

    @Override
    public MonAn findById(String id) {
        List<MonAn> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List select(String sql, Object... args) {
        List<MonAn> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    MonAn monAn = readFromResultSet(rs);
                    list.add(monAn);
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
    private MonAn readFromResultSet(ResultSet rs) throws SQLException {
        
        MonAn monAn = new MonAn();

        monAn.setMaMA(rs.getString("MaMA"));
        monAn.setTenMA(rs.getString("TenMA"));
        monAn.setHinhAnh(rs.getString("HinhAnh"));
        monAn.setGiaTien(rs.getLong("GiaTien"));
        monAn.setMaPL(rs.getString("MaPL"));
        monAn.setTenPL(rs.getString("TenPL"));
     
        return monAn;
//
    }
}
