/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.DichVu;
import com.happywedding.model.VaiTroTaiKhoan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class VaiTroTaiKhoanDAO {
    
    private final String SELECT_VaiTroTaiKhoan = "SELECT MaVT,TenVT FROM dbo.VaiTroTaiKhoan";
    private final String SELECT_BY_ID = "SELECT * FROM VaiTroTaiKhoan WHERE MaVT=?";
    
       public List<VaiTroTaiKhoan> select() {
        return select(SELECT_VaiTroTaiKhoan);
    }
    
    public VaiTroTaiKhoan findById(String id) {
     List<VaiTroTaiKhoan> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;  
    }
    
     private List select(String sql, Object... args) {
        List<VaiTroTaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    VaiTroTaiKhoan course = readFromResultSet(rs);
                    list.add(course);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private VaiTroTaiKhoan readFromResultSet(ResultSet rs) throws SQLException {
       VaiTroTaiKhoan pls = new VaiTroTaiKhoan();

        pls.setMaVT(rs.getString("MaVT"));
        pls.setTenVT(rs.getString("TenVT"));
        return pls;

    }
}
